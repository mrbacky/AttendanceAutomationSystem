/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.be.MockAttendanceRecord;
import attendance.be.MockSubjectAttendance;
import attendance.be.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class StudentAttendanceController implements Initializable {

    @FXML
    private TableView<MockAttendanceRecord> StudentAttTable;
    @FXML
    private TableColumn<?, ?> DayCellTableview;
    @FXML
    private TableColumn<?, ?> DateCellTableview;
    @FXML
    private TableColumn<?, ?> TimeCell;
    @FXML
    private TableColumn<?, ?> SubjectCell;
    @FXML
    private TableColumn<?, ?> StatusCell;
    @FXML
    private JFXButton DayBtn;
    @FXML
    private JFXButton WeekBtn;
    @FXML
    private JFXButton MonthBtn;
    @FXML
    private JFXButton OverallBtn;
    @FXML
    private MenuButton MenuStudentAtt;
    @FXML
    private JFXDatePicker DatePickerStudenAtt;

    /**
     * Initializes the controller class.
     */
//    ObservableList<AttendanceRecord> StudentAttendance = FXCollections.observableArrayList(
//            new AttendanceRecord("Monday","10-02-2020","3","SCO","Present"),
//            new AttendanceRecord("Monday","10-02-2020","3","SCO","Present"),
//            new AttendanceRecord("Monday","10-02-2020","3","SCO","Present"),
//            new AttendanceRecord("Monday","10-02-2020","3","SCO","Present")
//            );
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

//    public void displayAttendance() {
//        StudentAttTable.setItems(StudentAttendance);
//    }

    void setUser(User usr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
