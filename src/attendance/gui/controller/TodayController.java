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
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //  get models
        this.model = Model.getInstance();
        //this.courseCalModel = CourseCalModel.getInstance();
        //  load objects
        //courseCalModel.loadAllCourseCals();
        //setTableView();
        setUser();
        //initToggleButtons();
        showCurrentDate();

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

    private void setTableView() {
        colStart.setCellValueFactory(cellData -> cellData.getValue().startTimeProperty());
        colEnd.setCellValueFactory(cellData -> cellData.getValue().endTimeProperty());
        colSubject.setCellValueFactory(cellData -> cellData.getValue().courseNameProperty());
        colStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        tbvCal.setItems(courseCalModel.getObsCourseCals());
    }

    private void registerAttendance(ActionEvent event) {
        if (tbRegister.isSelected()) {
            
            
        }
    }


    @FXML
    private void handle_registerAttendance(ActionEvent event) {

//        CourseCal selectedSubject = tbvCal.getSelectionModel().getSelectedItem();
//
//        if (selectedSubject != null) {
//            selectedSubject.setStatus("Present");
//            //  temp insert
//            AttendanceRecord ar = new AttendanceRecord(selectedSubject.getStartTime(), selectedSubject.getEndTime(),
//                    selectedSubject.getCourseName(), selectedSubject.getStatus());
//
//            System.out.println("new attendance: " + ar.getStartTime() + " - " + ar.getEndTime() + " - " + ar.getCourseName() + " - " + ar.getStatus());
        

    }

}
