/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.Attendance;
import attendance.be.AttendanceRecord;
import attendance.be.Scedule;
import attendance.be.User;
import attendance.gui.model.AttendanceModel;
import attendance.gui.model.Model;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class TodayController implements Initializable {

    private Attendance attendance;
    @FXML
    private Label lblUsername;
    @FXML
    private ImageView imgUser;
    @FXML
    private Label lblTodayDate;
    @FXML
    private Label lblTime1;
    @FXML
    private Label lblTime2;
    @FXML
    private Label lblSubject1;
    @FXML
    private Label lblSubject2;
    @FXML
    private JFXToggleButton tglBtn1;
    @FXML
    private JFXToggleButton tglBtn2;

    private User user;

    private String UsernameLabel;
    private Model model;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<Scedule> tableview;
    @FXML
    private TableColumn<Scedule, Integer> starttimecol;
    @FXML
    private TableColumn<Scedule, Integer> endtimecol;
    @FXML
    private TableColumn<Scedule, String> subjects;

    private ObservableList<Scedule> sceduleList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.model = Model.getInstance();
        setUser();
        initToggleButtons();
        showCurrentDate();
        
        
        sceduleList.add(new Scedule(9,12, "SCO2"));
        sceduleList.add(new Scedule(9,12, "SDE2"));
        sceduleList.add(new Scedule(10,15, "ITO2"));
        
        starttimecol.setCellValueFactory(cellData -> cellData.getValue().startTimeProperty().asObject());
        endtimecol.setCellValueFactory(cellData -> cellData.getValue().endTimeProperty().asObject());
        subjects.setCellValueFactory(cellData -> cellData.getValue().subjectsProperty());
        
        tableview.setItems(sceduleList);
        
        
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
    private void setPresenttglButton(ActionEvent event) {
        
         sceduleList.get(0).setSubjects("Present");
        
       
        
    }

    @FXML
    private void setAbsenttglButton(ActionEvent event) {
        sceduleList.get(1).setSubjects("Absent");
        
    }
    
   

}
