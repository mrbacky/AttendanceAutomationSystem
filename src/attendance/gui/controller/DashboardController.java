/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.Attendance;
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
import javafx.scene.layout.AnchorPane;

public class DashboardController implements Initializable {

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
    // create pie chart data
    ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
            new PieChart.Data("Present", 75),
            new PieChart.Data("Absent", 25)
    );
    
    @FXML
    private PieChart pieChartATT;
    @FXML
    private AnchorPane pieChartAnchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayWeekChart();
        displaySemesterChart();
        
        displayPieChart();
        
    }

    public void displayWeekChart() {
        //barChartWeeklyStatus.setTitle("Country Summary");
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

    public void displayPieChart(){
        pieChartATT = new PieChart(pieData);
        //pieChartATT.setStyle("-fx-piechart-fill: green;");
        pieChartATT.setMaxSize(100, 100);
        pieChartAnchorPane.getChildren().clear();
        pieChartAnchorPane.getChildren().add(pieChartATT);
        
    }
}
