/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.Attendance;
import attendance.be.AttendanceRecord;
import attendance.be.CourseCal;
import attendance.be.User;
import attendance.gui.model.AttendanceModel;
import attendance.gui.model.CourseCalModel;
import attendance.gui.model.Model;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class TodayController implements Initializable {

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
    private Model model;
    private CourseCalModel courseCalModel;
    @FXML
    private AnchorPane anchorPane;
    private TableView<CourseCal> tbvCal;
    private TableColumn<CourseCal, String> colStart;
    private TableColumn<CourseCal, String> colEnd;
    private TableColumn<CourseCal, String> colSubject;
    private TableColumn<CourseCal, String> colStatus;
    @FXML
    private JFXComboBox<CourseCal> comboBoxCal;
    @FXML
    private JFXToggleButton tbRegister;

    private boolean threadRun = true;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //  get models
        this.model = Model.getInstance();
        this.courseCalModel = CourseCalModel.getInstance();
        //  load objects
        courseCalModel.loadAllCourseCals();
        setUser();
        //initToggleButtons();
        showCurrentDate();
        loadCalToComboBox();

    }

    public void initToggleButtons() {
        tglBtn1.setSelected(false);
        tglBtn2.setSelected(false);

        // Toggle Button Present 
        tglBtn1.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (tglBtn1.isSelected() == true) {
                tglBtn1.setText("Present");
                AttendanceModel x = AttendanceModel.getInstance();
                x.markAttendence(user, lblSubject1.getText());

            } else {
                tglBtn1.setText("Unregistered");

            }
        });

        // Toggle Button Absent 
        tglBtn2.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {

            if (tglBtn2.isSelected() == true) {
                tglBtn2.setText("Absent");

            } else {
                tglBtn2.setText("Unregistered");
            }
        });
    }

    public void showCurrentDate() {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();

        lblTodayDate.setText("Date: " + dateFormat.format(cal.getTime()));

    }

    private void setUser() {

        user = model.getCurrentUser();

        lblUsername.setText("Hello " + user.getName());

//        UsernameLabel = User.toString(user.getUsername());
//        lblUsername.setText(UsernameLabel);
    }

    @FXML
    private void handle_registerAttendance(ActionEvent event) {
        CourseCal calas = comboBoxCal.getSelectionModel().getSelectedItem();

        if (tbRegister.isSelected()) {
            tbRegister.setText("Present");

            calas.setStatusType(CourseCal.StatusType.PRESENT);
            // register method, send coursecal obj, send user ()
            // if endtime of subject <= currentTime. MARK... get it in BLL

        } else if (!tbRegister.isSelected()) {
            tbRegister.setText("Unregistered");
            calas.setStatusType(CourseCal.StatusType.UNREGISTERED);
        }

    }

    private void loadCalToComboBox() {
        comboBoxCal.getItems().setAll(courseCalModel.getObsCourseCals());
        comboBoxCal.getSelectionModel().select(0);
        Thread thread = new Thread() {
            public void run() {

                //Go to bll
                // take this : comboBoxCal.getItems()
                // do for each loop
                //Check if its unregistered and if enddate > current date
                //If it is . Set absent for single item
                //Return modified list
                //get current selected item index (So it doesnt interupt user)
                //clear list
                //add list again using modified list 
                //set previously chosen item index
                //call checkCurrentSelection()  -- modify TB to absent (maybe dont need)
                //sleep for 1 min (give or take)
                //Repeat (GOOGLE :D)
                onFinish(currentThread());
            }

        };
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
        CourseCal calItem = comboBoxCal.getSelectionModel().getSelectedItem();
        tbRegister.setDisable(false);
        if (calItem.getStatusType() == CourseCal.StatusType.ABSENT) {
            tbRegister.setText("Absent");
            tbRegister.setDisable(true);
            tbRegister.setSelected(true);
            //  later style button

        }else if(calItem.getStatusType() == CourseCal.StatusType.PRESENT) {
            tbRegister.setText("Present");
            tbRegister.setSelected(true);
        }else {
            tbRegister.setText("Unregistered");
            tbRegister.setSelected(false);
        }

    }
}
