/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.ResourceBundle;
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
    private TableView<?> StudentAttTable;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
