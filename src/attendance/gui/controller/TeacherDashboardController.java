/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.Student;
import attendance.be.User;
import attendance.bll.LogicManager;
import attendance.gui.model.CourseModel;
import attendance.gui.model.LessonModel;
import attendance.gui.model.ModelException;
import attendance.gui.model.StudentModel;
import attendance.gui.model.UserModel;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    private Label lblTotalOfStudents;

    private User user;
    private UserModel userModel;
    @FXML
    private ComboBox<Course> comboBoxCourses1;
    @FXML
    private TableView<Lesson> secondTableView;
    @FXML
    private TableColumn<Lesson, String> dateColumn;
    @FXML
    private TableColumn<Lesson, String> dayColumn;
    @FXML
    private TableColumn<Lesson, String> attendanceColumn;
    private LessonModel lessonModel;

    @FXML
    private BarChart<String, Integer> barChartWeekdayAbsence;

    boolean courseStart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//      get models
        this.studentModel = StudentModel.getInstance();
        this.userModel = UserModel.getInstance();
        this.courseModel = CourseModel.getInstance();
        this.lessonModel = LessonModel.getInstance();

//      load lists from backend
//        studentModel.loadAllStudents();
        //  courseModel.loadAllCourses(user.getId());
        //
        courseStart = true;
        setUser();
        courseModel.loadAllCourses(user.getId());
        setCoursesIntoComboBox();

        setTableViewsForCourseOverview();
        setTotalStudentLabel();
        setPresentStudentLabel();

        listenToCourseSelection();
        setSecondTableView();
        listenToOverviewTableViewSelection();
        listenToCourseSelectionForSelectedStudent();

//        comboBoxCourses.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Course>() {
//            @Override
//            public void changed(ObservableValue<? extends Course> observable, Course oldValue, Course newValue) {
//                comboBoxCourses1.setValue(newValue);
//
//            }
//        });
    }

    private void setTotalStudentLabel() {
        lblTotalOfStudents.textProperty().bind(Bindings.convert(studentModel.enrolledStudentsLabelProperty()));
    }

    private void setPresentStudentLabel() {
        lblStudentsPresent.textProperty().bind(Bindings.convert(studentModel.getAttendanceCountProperty()));
    }

    private void setTableViewsForCourseOverview() {
        studentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        absence.setCellValueFactory(new PropertyValueFactory<>("absencePercentage"));
        lessonCount.setCellValueFactory(new PropertyValueFactory<>("absenceCount"));

        tbvStudentAbsence.setItems(studentModel.getObsStudents());
    }

    private void setSecondTableView() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dayColumn.setCellValueFactory(new PropertyValueFactory<>("day"));
        attendanceColumn.setCellValueFactory(new PropertyValueFactory<>("statusType"));

        secondTableView.setItems(lessonModel.getObsStudentLessons());
    }

    private void listenToCourseSelection() {
        studentModel.startObserving(comboBoxCourses.getSelectionModel().getSelectedItem());
        System.out.println("THREAD COUNTTTTTT: " + Thread.activeCount());
        comboBoxCourses.getSelectionModel().selectedItemProperty().addListener((options, oldVal, newVal)
                -> {
            studentModel.stopObserving();
            studentModel.startObserving(newVal);
            System.out.println("THREAD COUNTTTTTT: " + Thread.activeCount());
        });
    }

    private void setCoursesIntoComboBox() {
        comboBoxCourses.getItems().clear();
        comboBoxCourses.getItems().addAll(courseModel.getObsCourses());
        comboBoxCourses.getSelectionModel().select(user.getCurrentSelectedCourse());
    }

    private void listenToOverviewTableViewSelection() {
        tbvStudentAbsence.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                lblstudentname.setText(newVal.getName());
                selectFirstCourse(newVal.getId());
                System.out.println("listenToOverviewTableViewSelection THREAD COUNTTTTTT: " + Thread.activeCount());
            }
        });
    }

    private void selectFirstCourse(int userId) {
        comboBoxCourses1.getItems().clear();
        courseModel.loadAllCourses(userId);
        comboBoxCourses1.getItems().addAll(courseModel.getObsCourses());
        comboBoxCourses1.getSelectionModel().select(0);
    }

    private void setUser() {
        try {
            this.user = userModel.getCurrentUser();
        } catch (ModelException ex) {
            Logger.getLogger(TeacherDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listenToCourseSelectionForSelectedStudent() {
        comboBoxCourses1.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {
            Student s = tbvStudentAbsence.getSelectionModel().getSelectedItem();
            if (s != null && newVal != null) {
                if (courseStart) {
                    lessonModel.startObserving(s, newVal);
                    courseStart = false;
                    System.out.println("listenToCourseSelectionForSelectedStudent#1 THREAD COUNTTTTTT: " + Thread.activeCount());
                }
                lessonModel.stopObserving();
                lessonModel.startObserving(s, newVal);
                setBarChart(s);
                System.out.println("listenToCourseSelectionForSelectedStudent#2 THREAD COUNTTTTTT: " + Thread.activeCount());
            }
            System.out.println("listenToCourseSelectionForSelectedStudent#3 THREAD COUNTTTTTT: " + Thread.activeCount());
        }
        );
    }

    private void setBarChart(Student selectedStudent) {
        barChartWeekdayAbsence.setAnimated(false);
        barChartWeekdayAbsence.setTitle("Absent lessons per weekday");
        List<Integer> lst = studentModel.getObsWeekdayAbsenceCount();
        studentModel.loadAllWeekdayAbsenceCount(selectedStudent.getId(), comboBoxCourses1.getSelectionModel().getSelectedItem().getId());
        barChartWeekdayAbsence.getData().clear();
        XYChart.Series dataQuery1 = new XYChart.Series();
        dataQuery1.setName("Absence");
        dataQuery1.getData().add(new XYChart.Data("Monday", lst.get(0)));
        dataQuery1.getData().add(new XYChart.Data("Tuesday", lst.get(1)));
        dataQuery1.getData().add(new XYChart.Data("Wednesday", lst.get(2)));
        dataQuery1.getData().add(new XYChart.Data("Thursday", lst.get(3)));
        dataQuery1.getData().add(new XYChart.Data("Friday", lst.get(4)));
        barChartWeekdayAbsence.getData().add(dataQuery1);

    }

}
