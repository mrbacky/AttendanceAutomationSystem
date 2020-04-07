/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.be.Course;
import attendance.be.Student;
import attendance.be.User;
import attendance.gui.model.concrete.CourseModel;
import attendance.gui.model.ModelException;
import attendance.gui.model.concrete.StudentModel;
import attendance.gui.model.concrete.UserModel;
import attendance.gui.model.interfaces.ICourseModel;
import attendance.gui.model.interfaces.IStudentModel;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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

    private IStudentModel studentModel;
    private ICourseModel courseModel;
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
    @FXML
    private ComboBox<Course> comboBoxCourses1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxCourses1.valueProperty().bind(comboBoxCourses.valueProperty());

        courseModel.loadAllCourses(user.getId());
        setCoursesIntoComboBox();

        setTableViewsForCourseOverview();
        setTotalStudentLabel();
        setPresentStudentLabel();
        listenToCourseSelection();

        comboBoxCourses.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Course>() {
            @Override
            public void changed(ObservableValue<? extends Course> observable, Course oldValue, Course newValue) {
                comboBoxCourses1.setValue(newValue);

            }
        });
    }

    public void injectModels(ICourseModel courseModel, IStudentModel studentModel) {
        this.courseModel = courseModel;
        this.studentModel = studentModel;

    }

    void setUser(User currentUser) {
        this.user = currentUser;
    }

    private void setTotalStudentLabel() {
        lblTotalOfStudents.textProperty().bind(Bindings.convert(studentModel.enrolledStudentsLabelProperty()));
    }

    private void setPresentStudentLabel() {
        lblStudentsPresent.textProperty().bind(Bindings.convert(studentModel.getAttendanceCountProperty()));
        studentModel.startObserving(comboBoxCourses.getSelectionModel().getSelectedItem());
    }

    private void setTableViewsForCourseOverview() {

        studentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        absence.setCellValueFactory(new PropertyValueFactory<>("absencePercentage"));
        lessonCount.setCellValueFactory(new PropertyValueFactory<>("absenceCount"));

        // TODO: change the method to using current date LATER.
        studentModel.loadAllStudents(comboBoxCourses.getSelectionModel().getSelectedItem(), LocalDateTime.parse("2020-03-09T14:29:00"));
        tbvStudentAbsence.setItems(studentModel.getObsStudents());
    }

    private void listenToCourseSelection() {
        comboBoxCourses.getSelectionModel().selectedItemProperty().addListener((options, oldVal, newVal)
                -> {
            studentModel.loadAllStudents(newVal, LocalDateTime.parse("2020-03-09T14:29:00"));
            studentModel.startObserving(newVal);
        });
    }

    private void setCoursesIntoComboBox() {
        comboBoxCourses.getItems().clear();
        comboBoxCourses.getItems().addAll(courseModel.getObservableCourseList());
        comboBoxCourses.getSelectionModel().select(user.getCurrentSelectedCourse());

    }

    @FXML
    private void getSelectedStudent(MouseEvent event) {
        Student selectedStudent = tbvStudentAbsence.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            lblstudentname.setText(selectedStudent.getName());
        }
    }

}
