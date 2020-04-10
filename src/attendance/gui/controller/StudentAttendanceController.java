package attendance.gui.controller;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.User;
import attendance.gui.model.concrete.CourseModel;
import attendance.gui.model.concrete.LessonModel;
import attendance.gui.model.ModelException;
import attendance.gui.model.concrete.UserModel;
import attendance.gui.model.interfaces.ICourseModel;
import attendance.gui.model.interfaces.ILessonModel;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author annem
 */
public class StudentAttendanceController implements Initializable {

    @FXML
    private TableView<Lesson> tblAttendance;
    @FXML
    private TableColumn<Lesson, String> colDay;
    @FXML
    private TableColumn<Lesson, String> colDate;
    @FXML
    private TableColumn<Lesson, String> colTime;
    @FXML
    private TableColumn<Lesson, String> colCourse;
    @FXML
    private TableColumn<Lesson, Lesson.StatusType> colStatus;
    @FXML
    private Label lblAbsence;
    @FXML
    private JFXComboBox<Course> cboCourses;

    private User user;
    private ICourseModel courseModel;
    private ILessonModel lessonModel;
    @FXML
    private Rectangle rectangle;
    @FXML
    private Label lblAbsence1;
    @FXML
    private Label lblAbsence2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        setCoursesIntoComboBox();
//        setTableView();
//        selectCourse();
//        lblAbsence.textProperty().bind(Bindings.convert(lessonModel.absencePercentageLabelProperty()));
    }

    public void injectModels(ICourseModel courseModel, ILessonModel lessonModel) {
        this.courseModel = courseModel;
        this.lessonModel = lessonModel;
        System.out.println("lessonModel in Overview: " + this.lessonModel);
        System.out.println("courseModel in OverView: " + this.courseModel);
    }

    public void setUser(User currentUser) {
        this.user = currentUser;
    }

    void initializeOverviewModule() {
        setCoursesIntoComboBox();
        setTableView();
        selectCourse();
        lblAbsence.textProperty().bind(Bindings.convert(lessonModel.absencePercentageLabelProperty()));
    }

    private void setCoursesIntoComboBox() {
        if (courseModel.getObservableCourseList() != null) {
            courseModel.loadAllCourses(user);
            cboCourses.getItems().clear();
            cboCourses.getItems().addAll(courseModel.getObservableCourseList());
        }

    }

    private void setTableView() {
        setColumnWidth();

        colDay.setCellValueFactory(new PropertyValueFactory<>("day"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("timeFrame"));
        colCourse.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("statusType"));

        tblAttendance.setItems(lessonModel.getObservableRecordList());
        lessonModel.loadAllRecords(user);
        System.out.println("setTableView");
    }

    private void setColumnWidth() {
        ObservableValue<Number> w = tblAttendance.widthProperty().divide(5);
        colDay.prefWidthProperty().bind(w);
        colDate.prefWidthProperty().bind(w);
        colTime.prefWidthProperty().bind(w);
        colCourse.prefWidthProperty().bind(w);
        colStatus.prefWidthProperty().bind(w);
    }

    private void selectCourse() {
        cboCourses.getSelectionModel().selectedItemProperty().addListener((options, oldVal, newVal) -> {
            if (newVal != null) {
                lessonModel.filterByCourse(user, newVal);
            }
        });
    }

    @FXML
    private void clearSelection(MouseEvent event) {
        if (!cboCourses.getSelectionModel().isEmpty()) {
            cboCourses.getSelectionModel().clearSelection();
            setTableView();
            System.out.println("clearSelection");
        }
    }

}
