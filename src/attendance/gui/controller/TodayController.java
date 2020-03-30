/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.Attendance;
import attendance.be.Lesson;
import attendance.be.User;
import attendance.gui.model.LessonModel;
import attendance.gui.model.UserModel;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
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
        System.out.println("lessons for today: " + lessonModel.getObsLessons());

        //initToggleButtons();
        showCurrentDate();
        loadLessonsToCB();

    }

    public void showCurrentDate() {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();

        lblTodayDate.setText("Date: " + dateFormat.format(cal.getTime()));

    }

    private void setUser() {

        user = userModel.getCurrentUser();

        lblUsername.setText("Hello " + user.getName());

//        UsernameLabel = User.toString(user.getUsername());
//        lblUsername.setText(UsernameLabel);
    }

    @FXML
    private void handle_registerAttendance(ActionEvent event) {
        Lesson lesson = comboBoxCal.getSelectionModel().getSelectedItem();

        if (tbRegister.isSelected()) {
            tbRegister.setText("Present");

            lesson.setStatusType(Lesson.StatusType.PRESENT);
            //int userId, int courseCalenderId, Lesson.StatusType status
            lessonModel.createRecord(user.getId(), lesson.getId(), lesson.getStatusType());
// register method, send coursecal obj, send user ()
            // if endtime of subject <= currentTime. MARK... get it in BLL

        } else if (!tbRegister.isSelected()) {
            tbRegister.setText("Unregistered");
            lesson.setStatusType(Lesson.StatusType.UNREGISTERED);
        }

    }

    private void loadLessonsToCB() {
        comboBoxCal.getItems().setAll(lessonModel.getObsLessons());
//        Lesson lessonNow = lessonModel.getObsLessons();
        comboBoxCal.getSelectionModel().select(0);

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

    public void onFinish(Thread thread) {
        try {
            if (threadRun) {
                thread.wait(1000, 0);// This is wrong. Check correct usage.
                thread.run();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TodayController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void checkCurrentSelection() {
        //for current selected item in combo box. Modify tbRegister to represent current status.
    }

    @FXML
    private void subjectStatusCheck(Event event) {
        System.out.println("ISCALLED");
        Lesson calItem = comboBoxCal.getSelectionModel().getSelectedItem();
        tbRegister.setDisable(false);
        if (calItem.getStatusType() == Lesson.StatusType.ABSENT) {
            tbRegister.setText("Absent");
            tbRegister.setDisable(true);
            tbRegister.setSelected(true);
            //  later style button

        } else if (calItem.getStatusType() == Lesson.StatusType.PRESENT) {
            tbRegister.setText("Present");
            tbRegister.setSelected(true);
        } else {
            tbRegister.setText("Unregistered");
            tbRegister.setSelected(false);
        }

    }

}
