package attendance.gui.controller;

import attendance.be.Lesson;
import attendance.be.User;
import attendance.gui.model.interfaces.ILessonModel;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.text.DateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.paint.Paint;

public class StudentTodayController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label lblUsername;
    @FXML
    private ImageView imgUser;
    @FXML
    private Label lblCurrentDate;
    @FXML
    private JFXComboBox<Lesson> cboLessons;
    @FXML
    private JFXToggleButton togglebtnRegistration;

    private User user;
    private ILessonModel lessonModel;
    private ScheduledExecutorService executor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setUser(User currentUser) {
        this.user = currentUser;
        lblUsername.setText("Hello " + user.getName());
    }

    public void injectModel(ILessonModel lessonModel) {
        this.lessonModel = lessonModel;
    }

    public void initializeTodayModule() {
        showCurrentDate();
        lessonModel.loadLessonsForToday(user, LocalDate.now());
        setLessonsToCB();
        selectInitialLesson();
        tbStatusSet();
        setupCheckerThread();
    }

    public void absenceGuard() {
        for (Lesson lesson : lessonModel.getLessonsForToday()) {
            if (lesson.getStatusType() == Lesson.StatusType.UNREGISTERED) {
                if (lesson.getEndTime().compareTo(LocalDateTime.now()) < 0) {
                    lesson.setStatusType(Lesson.StatusType.ABSENT);
                    lessonModel.registerAttendance(user, lesson);
                }
            }
        }
    }

    private void setupCheckerThread() {
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> {
                absenceGuard();
                refreshCombobox();
                tbStatusSet();
            });
        }, 1, 3, TimeUnit.SECONDS);

    }

    public void stopLessonChecker() {
        executor.shutdown();
    }

    private void showCurrentDate() {
        Date today = new Date(System.currentTimeMillis());
        DateFormat date = DateFormat.getDateInstance(DateFormat.FULL);
        lblCurrentDate.setText(date.format(today));
    }

    @FXML
    private void handle_registerAttendance(ActionEvent event) {
        Lesson lessonToUpdate = cboLessons.getSelectionModel().getSelectedItem();
        if (togglebtnRegistration.isSelected()) {
            togglebtnRegistration.setText("Present");
            togglebtnRegistration.setDisable(true);
            lessonToUpdate.setStatusType(Lesson.StatusType.PRESENT);
            lessonModel.registerAttendance(user, lessonToUpdate);
        } else if (!togglebtnRegistration.isSelected()) {
            togglebtnRegistration.setText("Unregistered");
        }
    }

    private void tbStatusSet() {
        Lesson lessonItem = cboLessons.getSelectionModel().getSelectedItem();
        togglebtnRegistration.setDisable(true);
        togglebtnRegistration.getStyleClass().removeAll("redTB", "greenTB");

        if (lessonItem != null) {
            if (lessonItem.getStatusType() == Lesson.StatusType.ABSENT) {
                togglebtnRegistration.setText("Absent");
                togglebtnRegistration.setSelected(true);
                setTBColors("Absent");
            } else if (lessonItem.getStatusType() == Lesson.StatusType.PRESENT) {
                togglebtnRegistration.setText("Present");
                togglebtnRegistration.setSelected(true);
                setTBColors("Present");
            } else {
                togglebtnRegistration.setText("Unregistered");
                togglebtnRegistration.setSelected(false);
                togglebtnRegistration.setDisable(false);
            }
        }

    }

    private void setTBColors(String type) {
        if (type.equals("Absent")) {
            togglebtnRegistration.setToggleColor(Paint.valueOf("#E5162F"));
            togglebtnRegistration.setToggleLineColor(Paint.valueOf("#FFB2BB"));

        } else if (type.equals("Present")) {
            togglebtnRegistration.setToggleColor(Paint.valueOf("#16b130"));
            togglebtnRegistration.setToggleLineColor(Paint.valueOf("#98f2a7"));
        }
    }

    @FXML
    private void subjectStatusCheck(ActionEvent event) {
        tbStatusSet();
    }

    private void refreshCombobox() {
        int lessonItem = cboLessons.getSelectionModel().getSelectedIndex();
        cboLessons.getSelectionModel().select(lessonItem);
    }

    private void setLessonsToCB() {
        if (lessonModel.getLessonsForToday() != null) {
            cboLessons.getItems().clear();
            cboLessons.getItems().setAll(lessonModel.getLessonsForToday());
        } else {
            cboLessons.setPromptText("No lessons today");
        }
    }

    private void selectInitialLesson() {
        List<Lesson> lessonList = lessonModel.getLessonsForToday();
        togglebtnRegistration.setDisable(true);
        for (Lesson lesson : lessonList) {
            if (lesson.getStartTime().compareTo(LocalDateTime.now()) < 0) {
                //  select latest lesson
                cboLessons.getSelectionModel().select(lesson);
            } else {
                cboLessons.getSelectionModel().select(0);
            }
        }
    }
}
