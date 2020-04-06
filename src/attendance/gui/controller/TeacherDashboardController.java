/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.be.Course;
import attendance.be.Student;
import attendance.be.Teacher;
import attendance.be.User;
import attendance.gui.model.CourseModel;
import attendance.gui.model.ModelException;
import attendance.gui.model.StudentModel;
import attendance.gui.model.UserModel;
import com.sun.jdi.connect.Connector;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class TeacherDashboardController implements Initializable {

    @FXML
    private TableView<Student> tbvStudentAbsence;
    @FXML
    private TableColumn<Student, String> studentName;
    @FXML
    private TableColumn<Student, Integer> absence;
    @FXML
    private TableColumn<Student, Integer> lessonCount;

    private StudentModel studentModel;
    private CourseModel courseModel;
    @FXML
    private ComboBox<Course> comboBoxCourses;
    @FXML
    private Label lblstudentname;
    @FXML
    private Label lblStudentsPresent;
    @FXML
    private Button BtnRefreshStudents;
    @FXML
    private Label lblTotalOfStudents;
    @FXML
    private Label lblRequestCount;

    private User user;
    private UserModel userModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//      get models
        this.studentModel = StudentModel.getInstance();
        this.userModel = UserModel.getInstance();
        this.courseModel = CourseModel.getInstance();

//      load lists from backend
//        studentModel.loadAllStudents();
        //  courseModel.loadAllCourses(user.getId());
        //  setters
        setUser();
        courseModel.loadAllCourses(user.getId());
        setCoursesIntoComboBox();
        setTableViews();
        setTotalStudentLabel();
        setPresentStudentLabel();
        listenToCourseSelection();        
    }

    private void setTotalStudentLabel(){
        lblTotalOfStudents.textProperty().bind(Bindings.convert(studentModel.enrolledStudentsLabelProperty()));        
    }
    
    private void setPresentStudentLabel() {
        lblStudentsPresent.textProperty().bind(Bindings.convert(studentModel.getAttendanceCountProperty()));
        studentModel.startObserving(comboBoxCourses.getSelectionModel().getSelectedItem());           
    }

    private void setTableViews() {

        studentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        absence.setCellValueFactory(new PropertyValueFactory<>("absencePercentage"));
        lessonCount.setCellValueFactory(new PropertyValueFactory<>("absenceCount"));

        // TODO: change the to using current date LATER.
        studentModel.loadAllStudents(comboBoxCourses.getSelectionModel().getSelectedItem().getId(), LocalDateTime.parse("2020-03-09T14:29:00"));
        tbvStudentAbsence.setItems(studentModel.getObsStudents());
    }

    private void listenToCourseSelection() {
        comboBoxCourses.getSelectionModel().selectedItemProperty().addListener((options, oldVal, newVal)
                -> {
            studentModel.loadAllStudents(newVal.getId(), LocalDateTime.parse("2020-03-09T14:29:00"));
            studentModel.startObserving(newVal);
        });        
    }

    private void setCoursesIntoComboBox() {
        comboBoxCourses.getItems().clear();
        comboBoxCourses.getItems().addAll(courseModel.getObsCourses());
        comboBoxCourses.getSelectionModel().select(user.getCurrentSelectedCourse());

    }

    @FXML
    private void getSelectedStudent(MouseEvent event) {
        Student selectedStudent = tbvStudentAbsence.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            lblstudentname.setText(selectedStudent.getName());
        }
    }

    private void setUser() {
        try {

            this.user = userModel.getCurrentUser();
        } catch (ModelException ex) {
            Logger.getLogger(TeacherDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
