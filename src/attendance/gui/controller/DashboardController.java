/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.Attendance;
import attendance.be.AttendanceRecord;
import attendance.be.SubjectAttendance;
import attendance.be.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class DashboardController implements Initializable {

    @FXML
    private BarChart<String, Number> barChartWeeklyStatus;
    @FXML
    private NumberAxis yWeekAxis;
    @FXML
    private CategoryAxis xWeekAxis;

    @FXML
    private BarChart<String, Number> barChartSemesterStatus;
    @FXML
    private NumberAxis ySemesterAxis;
    @FXML
    private CategoryAxis xSemesterAxis;

    @FXML
    private PieChart pieChartATT;
    @FXML
    private AnchorPane pieChartAnchorPane;
    @FXML
    private TableView<SubjectAttendance> tbvWeeklySubjectAttendance;

    @FXML
    private TableColumn<SubjectAttendance, String> colWeeklySubjects;
    @FXML
    private TableColumn<SubjectAttendance, Integer> colWeeklyOverall;
    @FXML
    private TableColumn<SubjectAttendance, String> colWeeklyDetails;

    @FXML
    private TableView<SubjectAttendance> tbvSemesterSubjectAttendance;
    @FXML
    private TableColumn<SubjectAttendance, String> colSemesterSubjects;
    @FXML
    private TableColumn<SubjectAttendance, Integer> colSemesterOverall;
    @FXML
    private TableColumn<SubjectAttendance, String> colSemesterDetails;

    final static String monday = "Mon";
    final static String tuesday = "Tue";
    final static String wednesday = "Wed";
    final static String thursday = "Thu";
    final static String friday = "Fri";

    final static String jan = "Jan";
    final static String feb = "Feb";
    final static String mar = "Mar";
    final static String apr = "Apr";
    final static String may = "May";
    final static String jun = "Jun";

    // create pie chart data
    ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
            new PieChart.Data("Present", 75),
            new PieChart.Data("Absent", 25)
    );

    ObservableList<SubjectAttendance> weeklySubjectAttendances = FXCollections.observableArrayList(
            new SubjectAttendance("SDE2.B.20", 84, "details"),
            new SubjectAttendance("SCO2.B.20", 78, "details"),
            new SubjectAttendance("ITO2.B.20", 69, "details"),
            new SubjectAttendance("DBOS.B.20", 57, "details")
    );

    ObservableList<SubjectAttendance> semesterSubjectAttendances = FXCollections.observableArrayList(
            new SubjectAttendance("SDE2.B.20", 74, "details"),
            new SubjectAttendance("SCO2.B.20", 62, "details"),
            new SubjectAttendance("ITO2.B.20", 30, "details"),
            new SubjectAttendance("DBOS.B.20", 74, "details")
    );

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTableViews();

        displayWeekChart();
        displaySemesterChart();
        displayPieChart();
        displayWeeklyAttendance();
        displaySemesterAttendances();

    }

    public void setTableViews() {
        colWeeklySubjects.setCellValueFactory(new PropertyValueFactory<>("name"));
        colWeeklyOverall.setCellValueFactory(new PropertyValueFactory<>("overall"));
        colWeeklyDetails.setCellValueFactory(new PropertyValueFactory<>("details"));

        colSemesterSubjects.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSemesterOverall.setCellValueFactory(new PropertyValueFactory<>("overall"));
        colSemesterDetails.setCellValueFactory(new PropertyValueFactory<>("details"));
    }

    public void displayWeekChart() {
        xWeekAxis.setLabel("Days");
        yWeekAxis.setLabel("Attendance");

        XYChart.Series daySeries = new XYChart.Series();
        daySeries.setName("Attendance");
        daySeries.getData().add(new XYChart.Data(monday, 50));
        daySeries.getData().add(new XYChart.Data(tuesday, 0));
        daySeries.getData().add(new XYChart.Data(wednesday, 100));
        daySeries.getData().add(new XYChart.Data(thursday, 50));
        daySeries.getData().add(new XYChart.Data(friday, 100));

        barChartWeeklyStatus.getData().addAll(daySeries);
    }

    public void displaySemesterChart() {
        //barChartWeeklyStatus.setTitle("Country Summary");
        xWeekAxis.setLabel("Months");
        yWeekAxis.setLabel("Attendance");

        XYChart.Series semesterSeries = new XYChart.Series();
        semesterSeries.setName("Attendance");
        semesterSeries.getData().add(new XYChart.Data(jan, 80));
        semesterSeries.getData().add(new XYChart.Data(feb, 30));
        semesterSeries.getData().add(new XYChart.Data(mar, 100));
        semesterSeries.getData().add(new XYChart.Data(apr, 90));
        semesterSeries.getData().add(new XYChart.Data(may, 75));
        semesterSeries.getData().add(new XYChart.Data(jun, 60));

        barChartSemesterStatus.getData().addAll(semesterSeries);
    }

    public void displayPieChart() {
        pieChartATT = new PieChart(pieData);
        //pieChartATT.setStyle("-fx-piechart-fill: green;");
        pieChartATT.setMaxSize(100, 100);
        pieChartAnchorPane.getChildren().clear();
        pieChartAnchorPane.getChildren().add(pieChartATT);

    }

    public void displayWeeklyAttendance() {
        tbvWeeklySubjectAttendance.setItems(weeklySubjectAttendances);
    }

    public void displaySemesterAttendances() {
        tbvSemesterSubjectAttendance.setItems(semesterSubjectAttendances);
    }

    void setUser(User usr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
