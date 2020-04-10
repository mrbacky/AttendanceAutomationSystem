package attendance.gui.controller;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.User;
import attendance.gui.model.interfaces.ICourseModel;
import attendance.gui.model.interfaces.IRecordModel;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author annem
 */
public class StudentOverviewController implements Initializable {

    @FXML
    private JFXComboBox<Course> cboCourses;
    @FXML
    private TableView<Lesson> tblAttendance;
    @FXML
    private TableColumn<Lesson, String> colDay;
    @FXML
    private TableColumn<Lesson, String> colDate;
    @FXML
    private TableColumn<Lesson, String> colTime;
    @FXML
    private TableColumn<Lesson, String> colCourse;
    @FXML
    private TableColumn<Lesson, Lesson.StatusType> colStatus;
    @FXML
    private Rectangle rectangle;
    @FXML
    private Label lblAbsence;
    @FXML
    private Label lblAbsence1;
    @FXML
    private Label lblAbsence2;

    private User user;
    private ICourseModel courseModel;
    private IRecordModel recordModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        setCoursesIntoComboBox();
//        setTableView();
//        selectCourse();
//        lblAbsence.textProperty().bind(Bindings.convert(lessonModel.absencePercentageLabelProperty()));
    }

    public void injectModels(ICourseModel courseModel, IRecordModel recordModel) {
        this.courseModel = courseModel;
        this.recordModel = recordModel;
    }

    public void setUser(User currentUser) {
        this.user = currentUser;
    }

    public void initializeOverviewModule() {
        setCoursesIntoComboBox();
        setTableView();
        selectCourse();
        lblAbsence.textProperty().bind(Bindings.convert(recordModel.absencePercentageLabelProperty()));
    }

    private void setCoursesIntoComboBox() {
        if (courseModel.getCourseList() != null) {
            courseModel.loadAllCourses(user);
            cboCourses.getItems().clear();
            cboCourses.getItems().addAll(courseModel.getCourseList());
        }
    }

    private void setTableView() {
        setColumnWidth();

        colDay.setCellValueFactory(new PropertyValueFactory<>("day"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("timeFrame"));
        colCourse.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("statusType"));

        tblAttendance.setItems(recordModel.getRecordList());
        recordModel.loadAllRecords(user);
    }

    private void setColumnWidth() {
        ObservableValue<Number> w = tblAttendance.widthProperty().divide(5);
        colDay.prefWidthProperty().bind(w);
        colDate.prefWidthProperty().bind(w);
        colTime.prefWidthProperty().bind(w);
        colCourse.prefWidthProperty().bind(w);
        colStatus.prefWidthProperty().bind(w);
    }

    private void selectCourse() {
        cboCourses.getSelectionModel().selectedItemProperty().addListener((options, oldVal, newVal) -> {
            if (newVal != null) {
                recordModel.filterRecordsByCourse(user, newVal);
            }
        });
    }

    @FXML
    private void clearSelection(MouseEvent event) {
        if (!cboCourses.getSelectionModel().isEmpty()) {
            cboCourses.getSelectionModel().clearSelection();
            setTableView();
        }
    }

}
