package attendance.gui.controller;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.Student;
import attendance.be.User;
import attendance.gui.model.CourseModel;
import attendance.gui.model.LessonModel;
import attendance.gui.model.ModelException;
import attendance.gui.model.StudentModel;
import attendance.gui.model.UserModel;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    boolean firstTableViewSelection;

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
        firstTableViewSelection = true;
        setUser();
        courseModel.loadAllCourses(user.getId());
        setCoursesIntoComboBox();

        setTableViewsForCourseOverview();
        setTotalStudentLabel();
        setPresentStudentLabel();

        listenToCourseSelection();
        setSecondTableView();
        setBarChart();
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

        comboBoxCourses.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {
            studentModel.stopObserving();
            studentModel.startObserving(newVal);
        });
    }

    private void setCoursesIntoComboBox() {
        comboBoxCourses.getItems().clear();
        comboBoxCourses.getItems().addAll(courseModel.getObsCourses());
        comboBoxCourses.getSelectionModel().select(user.getCurrentSelectedCourse());
    }

    private void listenToOverviewTableViewSelection() {
        tbvStudentAbsence.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (tbvStudentAbsence.getSelectionModel().isEmpty()) {
                lblstudentname.setText("");
                comboBoxCourses1.valueProperty().set(null);
                courseModel.getObsCourses().clear();
                lessonModel.getObsStudentLessons().clear();
                lessonModel.getObsWeekdayAbsenceCount().clear();
            }
            if (newVal != null) {
                lblstudentname.setText(newVal.getName());
                selectFirstCourse(newVal);
                setBarChart();
                Course c = comboBoxCourses1.getSelectionModel().getSelectedItem();
                if (c != null) {
                    if (firstTableViewSelection) {
                        setBarChart();
                        lessonModel.startObserving(newVal, c);
                        firstTableViewSelection = false;
                    }
                    setBarChart();
                    lessonModel.stopObserving();
                    lessonModel.startObserving(newVal, c);
                }
            }
        });
    }

    private void selectFirstCourse(Student student) {
        if (student != null) {
            comboBoxCourses1.getItems().clear();
            courseModel.loadAllCourses(student.getId());
            comboBoxCourses1.getItems().addAll(courseModel.getObsCourses());
            comboBoxCourses1.getSelectionModel().select(comboBoxCourses.getValue());
        }
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
            if (comboBoxCourses1.getSelectionModel().isEmpty()) {
                lessonModel.getObsStudentLessons().clear();
                lessonModel.getObsWeekdayAbsenceCount().clear();
            }
            Student s = tbvStudentAbsence.getSelectionModel().getSelectedItem();
            if (s != null && newVal != null) {
                if (firstTableViewSelection) {
                    setBarChart();
                    lessonModel.startObserving(s, newVal);
                    firstTableViewSelection = false;
                }
                setBarChart();
                lessonModel.stopObserving();
                lessonModel.startObserving(s, newVal);
            }
        }
        );
    }

    private void setBarChart() {
        barChartWeekdayAbsence.setAnimated(false);
        barChartWeekdayAbsence.setTitle("Absent lessons per weekday");
        ObservableList<BarChart.Data<String, Integer>> data = lessonModel.getObsWeekdayAbsenceCount();
        if (!data.isEmpty()) {
            barChartWeekdayAbsence.getData().clear();
            XYChart.Series<String, Integer> dataQuery1 = new XYChart.Series<>("Absence", data);
            barChartWeekdayAbsence.getData().setAll(dataQuery1);
        }
    }

}
