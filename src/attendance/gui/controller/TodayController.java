/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.Attendance;
import attendance.be.Lesson;
import attendance.be.User;
import attendance.gui.model.ModelException;
import attendance.gui.model.LessonModel;
import attendance.gui.model.UserModel;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import java.awt.Color;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.lang.String;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

public class TodayController implements Initializable {

    public static final String IN_TODAY_COURSE_VIEW_PATH = "/attendance/gui/view/ChooseSubjectAfterLogin.fxml";

    private Attendance attendance;
    @FXML
    private Label lblUsername;
    @FXML
    private ImageView imgUser;
    @FXML
    private Label lblTodayDate;
    private Label lblSubject1;
    private JFXToggleButton tglBtn1;
    private JFXToggleButton tglBtn2;

    private User user;

    private String UsernameLabel;
    private UserModel userModel;
    private LessonModel lessonModel;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXComboBox<Lesson> comboBoxCal;
    @FXML
    private JFXToggleButton tbRegister;

    private boolean threadRun = true;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //  get models
        this.userModel = UserModel.getInstance();
        this.lessonModel = LessonModel.getInstance();
        //  load objects
        setUser();
        LocalDate currentDate = LocalDate.now();
        lessonModel.loadAllLessons(user.getId(), currentDate);
        System.out.println("print from Today controller > lessons for student: " + lessonModel.getObsLessons());
        showCurrentDate();
        loadLessonsToCB();

    }

    public void showCurrentDate() {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        lblTodayDate.setText("Date: " + dateFormat.format(cal.getTime()));
    }

    private void setUser() {
        try {
            user = userModel.getCurrentUser();
        } catch (ModelException ex) {
            Logger.getLogger(TodayController.class.getName()).log(Level.SEVERE, null, ex);
        }

        lblUsername.setText("Hello " + user.getName());

    }

    @FXML
    private void handle_registerAttendance(ActionEvent event) {
        Lesson lessonToUpdate = comboBoxCal.getSelectionModel().getSelectedItem();

        if (tbRegister.isSelected()) {
            tbRegister.setText("Present");
            tbRegister.setDisable(true);

            lessonToUpdate.setStatusType(Lesson.StatusType.PRESENT);
            //int userId, int courseCalenderId, Lesson.StatusType status
            lessonModel.createRecord(user.getId(), lessonToUpdate);

            //   createRecords returns status PRESENT - set present but
            //      if Lesson.getstatus == PRESENT && tb is not selected 
            //    is selected(true)
            // register method, send coursecal obj, send user ()
            // if endtime of subject <= currentTime. MARK... get it in BLL
        } else if (!tbRegister.isSelected()) {
            tbRegister.setText("Unregistered");
//            lesson.setStatusType(Lesson.StatusType.UNREGISTERED);
        }

    }

    private void tbStatusSet() {
        Lesson lessonItem = comboBoxCal.getSelectionModel().getSelectedItem();
        tbRegister.setDisable(true);
        tbRegister.getStyleClass().removeAll("redTB", "greenTB");
        if (lessonItem.getStatusType() == Lesson.StatusType.ABSENT) {
            tbRegister.setText("Absent");
            tbRegister.setSelected(true);
            setTBColors("Absent");
        } else if (lessonItem.getStatusType() == Lesson.StatusType.PRESENT) {
            tbRegister.setText("Present");
            tbRegister.setSelected(true);
            setTBColors("Present");
        } else {
            tbRegister.setText("Unregistered");
            tbRegister.setSelected(false);
            tbRegister.setDisable(false);
        }
    }

    private void setTBColors(String type) {
        if (type.equals("Absent")) {
            tbRegister.setToggleColor(Paint.valueOf("#E5162F"));
            tbRegister.setToggleLineColor(Paint.valueOf("#FFB2BB"));

        } else if (type.equals("Present")) {
            tbRegister.setToggleColor(Paint.valueOf("#16b130"));
            tbRegister.setToggleLineColor(Paint.valueOf("#98f2a7"));
        }
    }

    @FXML
    private void subjectStatusCheck(ActionEvent event) {
        tbStatusSet();

    }

    private void loadLessonsToCB() {
        comboBoxCal.getItems().setAll(lessonModel.getObsLessons());
        List<Lesson> lessonList = lessonModel.getObsLessons();
        tbRegister.setDisable(true);
        //  select last one
        for (Lesson lesson : lessonList) {
            if (lesson.getStartTime().compareTo(LocalDateTime.now()) < 0) {
                comboBoxCal.getSelectionModel().select(lesson);
            } else {
                comboBoxCal.getSelectionModel().select(0);
            }
        }
        tbStatusSet();

    }

    public void onFinish(Thread thread) {
        try {
            if (threadRun) {
                thread.wait(1000, 0);// This is wrong. Check correct usage.
                thread.run();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TodayController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        //        Thread thread = new Thread() {
//            public void run() {
//
//                //Go to bll
//                // take this : comboBoxCal.getItems()
//                // do for each loop
//                //Check if its unregistered and if enddate > current date
//                //If it is . Set absent for single item
//                //Return modified list
//                //get current selected item index (So it doesnt interupt user)
//                //clear list
//                //add list again using modified list 
//                //set previously chosen item index
//                //call checkCurrentSelection()  -- modify TB to absent (maybe dont need)
//                //sleep for 1 min (give or take)
//                //Repeat (GOOGLE :D)
//                onFinish(currentThread());
//            }
//
//        };
        //thread.run();
        //threadRun=false;
    }

    private void checkCurrentSelection() {
        //for current selected item in combo box. Modify tbRegister to represent current status.
    }
}
