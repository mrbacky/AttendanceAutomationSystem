/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.be.Course;
import attendance.be.Student;
import attendance.gui.model.CourseModel;
import attendance.gui.model.StudentModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitMenuButton;
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
    private CourseModel courseModel;
    @FXML
    private ComboBox<Course> comboBoxCourses;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //  get models
        this.studentModel = StudentModel.getInstance();
        this.courseModel = CourseModel.getInstance();
        //  load lists from backend
        studentModel.loadAllStudents();
        courseModel.loadAllCourses();
        //  setters
        setCoursesIntoComboBox();
        setTableViews();

    }

    private void setTableViews() {
        studentName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        absence.setCellValueFactory(cellData -> cellData.getValue().absenceProperty().asObject());
        //  set student observable list into tableview
        tbvStudentAbsence.setItems(studentModel.getObsStudents());
    }

    private void setCoursesIntoComboBox() {
        
        
        comboBoxCourses.getItems().clear();
        
        comboBoxCourses.getItems().addAll(courseModel.getObsCourses());
        
    }

}
