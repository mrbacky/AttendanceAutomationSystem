package attendance.gui.controller;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.Student;
import attendance.be.User;
import attendance.gui.model.interfaces.ICourseModel;
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
    private ComboBox<Course> cboTeacherCourses;
    @FXML
    private Label lblPresentStudentsCount;
    @FXML
    private Label lblEnrolledStudentCount;
    @FXML
    private TableView<Student> tblCourseAbsenceOverview;
    @FXML
    private TableColumn<Student, String> colStudentName;
    @FXML
    private TableColumn<Student, Integer> colAbsencePercentage;
    @FXML
    private TableColumn<Student, Integer> colAbsentLessons;

    @FXML
    private Label lblStudentName;
    @FXML
    private ComboBox<Course> cboStudentCourses;
    @FXML
    private TableView<Lesson> tblStudentAbsenceOverview;
    @FXML
    private TableColumn<Lesson, String> colDate;
    @FXML
    private TableColumn<Lesson, String> colDay;
    @FXML
    private TableColumn<Lesson, String> colStatus;
    @FXML
    private BarChart<String, Integer> barChartWeekdayAbsence;

    private User user;
    private IStudentModel studentModel;
    private ICourseModel courseModel;
    private IRecordModel recordModel;
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
        courseModel.loadAllCourses(user);
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
        lblEnrolledStudentCount.textProperty().bind(Bindings.convert(studentModel.enrolledStudentsLabelProperty()));
    }

    private void setPresentStudentLabel() {
        lblPresentStudentsCount.textProperty().bind(Bindings.convert(studentModel.getAttendanceCountProperty()));
    }

    private void setTableViewsForCourseOverview() {
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAbsencePercentage.setCellValueFactory(new PropertyValueFactory<>("absencePercentage"));
        colAbsentLessons.setCellValueFactory(new PropertyValueFactory<>("absenceCount"));

        tblCourseAbsenceOverview.setItems(studentModel.getStudentList());
    }

    private void setSecondTableView() {
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDay.setCellValueFactory(new PropertyValueFactory<>("day"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("statusType"));

        tblStudentAbsenceOverview.setItems(recordModel.getRecordList());
    }

    private void listenToCourseSelection() {
        studentModel.startObserving(cboTeacherCourses.getSelectionModel().getSelectedItem());

        cboTeacherCourses.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {
            studentModel.stopObserving();
            studentModel.startObserving(newVal);
        });
    }

    private void setCoursesIntoComboBox() {
        cboTeacherCourses.getItems().clear();
        cboTeacherCourses.getItems().addAll(courseModel.getCourseList());
        cboTeacherCourses.getSelectionModel().select(user.getCurrentSelectedCourse());
    }

    private void listenToOverviewTableViewSelection() {
        tblCourseAbsenceOverview.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (tblCourseAbsenceOverview.getSelectionModel().isEmpty()) {
                lblStudentName.setText("No selected student.");
                cboStudentCourses.valueProperty().set(null);
                courseModel.getCourseList().clear();
                recordModel.getRecordList().clear();
                recordModel.getWeekdayAbsenceCount().clear();
            }
            if (newVal != null) {
                lblStudentName.setText(newVal.getName());
                selectFirstCourse(newVal);
                setBarChart();
                Course c = cboStudentCourses.getSelectionModel().getSelectedItem();
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
            cboStudentCourses.getItems().clear();
            courseModel.loadAllCourses(student);
            cboStudentCourses.getItems().addAll(courseModel.getCourseList());
            cboStudentCourses.getSelectionModel().select(cboTeacherCourses.getValue());
        }
    }

    private void listenToCourseSelectionForSelectedStudent() {
        cboStudentCourses.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {
            if (cboStudentCourses.getSelectionModel().isEmpty()) {
                recordModel.getRecordList().clear();
                recordModel.getWeekdayAbsenceCount().clear();
            }
            Student s = tblCourseAbsenceOverview.getSelectionModel().getSelectedItem();
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
