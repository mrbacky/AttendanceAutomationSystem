package attendance.gui.controller;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.Student;
import attendance.be.User;
import attendance.gui.model.interfaces.ICourseModel;
import attendance.gui.model.interfaces.ILessonModel;
import attendance.gui.model.interfaces.IRecordModel;
import attendance.gui.model.interfaces.IStudentModel;
import java.net.URL;
import java.util.ResourceBundle;
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

    private IStudentModel studentModel;
    private ICourseModel courseModel;
    private IRecordModel recordModel;
    @FXML
    private ComboBox<Course> comboBoxCourses;
    @FXML
    private Label lblstudentname;
    @FXML
    private Label lblStudentsPresent;
    @FXML
    private Label lblTotalOfStudents;

    private User user;
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

    @FXML
    private BarChart<String, Integer> barChartWeekdayAbsence;

    boolean firstTableViewSelection;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    void injectModels(ICourseModel courseModel, IStudentModel studentModel, IRecordModel recordModel) {
        this.courseModel = courseModel;
        this.studentModel = studentModel;
        this.recordModel = recordModel;
    }

    void setUser(User currentUser) {
        this.user = currentUser;
    }

    void initializeView() {
        firstTableViewSelection = true;
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

        tbvStudentAbsence.setItems(studentModel.getStudentList());
    }

    private void setSecondTableView() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dayColumn.setCellValueFactory(new PropertyValueFactory<>("day"));
        attendanceColumn.setCellValueFactory(new PropertyValueFactory<>("statusType"));

        secondTableView.setItems(recordModel.getRecordList());
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
        comboBoxCourses.getItems().addAll(courseModel.getCourseList());
        comboBoxCourses.getSelectionModel().select(user.getCurrentSelectedCourse());
    }

    private void listenToOverviewTableViewSelection() {
        tbvStudentAbsence.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (tbvStudentAbsence.getSelectionModel().isEmpty()) {
                lblstudentname.setText("");
                comboBoxCourses1.valueProperty().set(null);
                courseModel.getCourseList().clear();
                recordModel.getRecordList().clear();
                recordModel.getWeekdayAbsenceCount().clear();
            }
            if (newVal != null) {
                lblstudentname.setText(newVal.getName());
                selectFirstCourse(newVal);
                setBarChart();
                Course c = comboBoxCourses1.getSelectionModel().getSelectedItem();
                if (c != null) {
                    if (firstTableViewSelection) {
                        setBarChart();
                        recordModel.startObserving(newVal, c);
                        firstTableViewSelection = false;
                    }
                    setBarChart();
                    recordModel.stopObserving();
                    recordModel.startObserving(newVal, c);
                }
            }
        });
    }

    private void selectFirstCourse(Student student) {
        if (student != null) {
            comboBoxCourses1.getItems().clear();
            courseModel.loadAllCourses(student.getId());
            comboBoxCourses1.getItems().addAll(courseModel.getCourseList());
            comboBoxCourses1.getSelectionModel().select(comboBoxCourses.getValue());
        }
    }

    private void listenToCourseSelectionForSelectedStudent() {
        comboBoxCourses1.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {
            if (comboBoxCourses1.getSelectionModel().isEmpty()) {
                recordModel.getRecordList().clear();
                recordModel.getWeekdayAbsenceCount().clear();
            }
            Student s = tbvStudentAbsence.getSelectionModel().getSelectedItem();
            if (s != null && newVal != null) {
                if (firstTableViewSelection) {
                    setBarChart();
                    recordModel.startObserving(s, newVal);
                    firstTableViewSelection = false;
                }
                setBarChart();
                recordModel.stopObserving();
                recordModel.startObserving(s, newVal);
            }
        }
        );
    }

    private void setBarChart() {
        barChartWeekdayAbsence.setAnimated(false);
        barChartWeekdayAbsence.setTitle("Absent lessons per weekday");
        ObservableList<BarChart.Data<String, Integer>> data = recordModel.getWeekdayAbsenceCount();
        if (!data.isEmpty()) {
            barChartWeekdayAbsence.getData().clear();
            XYChart.Series<String, Integer> dataQuery1 = new XYChart.Series<>("Absence", data);
            barChartWeekdayAbsence.getData().setAll(dataQuery1);
        }
    }

}
