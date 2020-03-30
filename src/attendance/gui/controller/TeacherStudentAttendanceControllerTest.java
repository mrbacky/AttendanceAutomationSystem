/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author rados
 */
public class TeacherStudentAttendanceControllerTest implements Initializable {

    @FXML
    private TableView<?> tbvStudentAbsence;
    @FXML
    private TableColumn<?, ?> studentName;
    @FXML
    private TableColumn<?, ?> absence;
    @FXML
    private TableColumn<?, ?> absence1;
    @FXML
    private Label lblstudentname;
    @FXML
    private BarChart<?, ?> barChart;
    @FXML
    private Label lblStudentsPresent;
    @FXML
    private Button BtnRefreshStudents;
    @FXML
    private Label lblTotalOfStudents;
    @FXML
    private Label lblRequestCount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void getSelectedStudent(MouseEvent event) {
    }
    
}
