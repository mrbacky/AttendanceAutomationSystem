/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.be.AttendanceRecord;
import attendance.be.SubjectAttendance;
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
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class StudentAttendanceController implements Initializable {

    @FXML
    private TableView<AttendanceRecord> StudentAttTable;

    @FXML
    private TableColumn<AttendanceRecord, String> DayCellTableview;
    @FXML
    private TableColumn<AttendanceRecord, String> DateCellTableview;
    @FXML
    private TableColumn<AttendanceRecord, String> TimeCell;
    @FXML
    private TableColumn<AttendanceRecord, String> SubjectCell;
    @FXML
    private TableColumn<AttendanceRecord, String> StatusCell;
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
    ObservableList<AttendanceRecord> StudentAttendance = FXCollections.observableArrayList(
            new AttendanceRecord("Monday", "10-02-2020", "3", "SCO", "Present"),
            new AttendanceRecord("Monday", "10-02-2020", "3", "SCO", "Present"),
            new AttendanceRecord("Monday", "10-02-2020", "3", "SCO", "Present"),
            new AttendanceRecord("Monday", "10-02-2020", "3", "SCO", "Present")
    );

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayAttendance();
        setTableViews();
    }

    public void setTableViews() {
        DayCellTableview.setCellValueFactory(new PropertyValueFactory<>("day"));
        DateCellTableview.setCellValueFactory(new PropertyValueFactory<>("date"));
        TimeCell.setCellValueFactory(new PropertyValueFactory<>("time"));
        SubjectCell.setCellValueFactory(new PropertyValueFactory<>("subject"));
        StatusCell.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    public void displayAttendance() {
        StudentAttTable.setItems(StudentAttendance);

    }
}
