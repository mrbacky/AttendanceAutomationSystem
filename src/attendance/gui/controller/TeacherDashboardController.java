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
import attendance.bll.LogicManager;
import attendance.gui.model.CourseModel;
import attendance.gui.model.ModelException;
import attendance.gui.model.StudentModel;
import attendance.gui.model.UserModel;
import com.sun.jdi.connect.Connector;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
    @FXML
    private ComboBox<Course> comboBoxCourses1;
   
    @FXML
    private BarChart<String, Integer> barChartWeekdayAbsence;

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
        //  
        comboBoxCourses1.valueProperty().bind(comboBoxCourses.valueProperty());
        
        setUser();
        courseModel.loadAllCourses(user.getId());
        setCoursesIntoComboBox();
        setTableViews();
        comboBoxCourses.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Course>() {
            @Override
            public void changed(ObservableValue<? extends Course> observable, Course oldValue, Course newValue) {
               comboBoxCourses1.setValue(newValue);
               
            }
        });
        
        setBarChart();
    }

    private void setTableViews() {

        studentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        absence.setCellValueFactory(new PropertyValueFactory<>("absencePercentage"));
        lessonCount.setCellValueFactory(new PropertyValueFactory<>("absenceCount"));

        // set student observable list into tableview
        // provide the method with the correct parameters.
        // change the column text.
        // TODO: change the to using current date LATER.
        studentModel.loadAllStudents(comboBoxCourses.getSelectionModel().getSelectedItem().getId(), LocalDateTime.parse("2020-03-09T14:29:00"));
        changeSelection();
        tbvStudentAbsence.setItems(studentModel.getObsStudents());
    }

    private void changeSelection() {
        comboBoxCourses.getSelectionModel().selectedItemProperty().addListener((options, oldVal, newVal)
                -> {
            studentModel.loadAllStudents(newVal.getId(), LocalDateTime.parse("2020-03-09T14:29:00"));
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

    private void setBarChart() {
        List<Integer> lst = studentModel.getObsWeekdayAbsenceCount();
        //TODO: change to use real parameters from ComboBox and selection of TableView.
        studentModel.loadAllWeekdayAbsenceCount(9, 1);
       
        barChartWeekdayAbsence.setTitle("Absent lessons per weekday");
        
        XYChart.Series dataQuery1 = new XYChart.Series();
        dataQuery1.setName("Absence");           
        dataQuery1.getData().add(new XYChart.Data("Monday", lst.get(0)));
        dataQuery1.getData().add(new XYChart.Data("Tuesday", lst.get(1)));
        dataQuery1.getData().add(new XYChart.Data("Wednesday", lst.get(2)));
        dataQuery1.getData().add(new XYChart.Data("Thursday", lst.get(3)));
        dataQuery1.getData().add(new XYChart.Data("Friday", lst.get(4)));
        barChartWeekdayAbsence.getData().add(dataQuery1);
        
        //TODO: make method to listen to ComboBox selection.
        //comboBoxCourses.getSelectionModel().selectedItemProperty().addListener((options, oldVal, newVal)
        //        -> {
        //    studentModel.loadAllWeekdayAbsenceCount(tbvStudentAbsence.getSelectionModel().getSelectedItem().getId(), newVal.getId());
        //});
    }
    

}
