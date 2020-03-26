/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.be.Student;
import attendance.gui.model.StudentModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TeacherStudentAttendanceController implements Initializable {

    @FXML
    private TableView<Student> tbvStudentAbsence;
    @FXML
    private TableColumn<Student, String> studentName;
    @FXML
    private TableColumn<Student, Double> absence;

    private StudentModel studentModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.studentModel = StudentModel.getInstance();
        setTableViews();

    }

    private void setTableViews() {
        studentName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        absence.setCellValueFactory(cellData -> cellData.getValue().absenceProperty().asObject());
        tbvStudentAbsence.setItems(studentModel.getObsStudents());
        studentModel.loadAllStudents();

    }

}
