/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.Attendance;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class RootLayoutController implements Initializable {

    @FXML
    private HBox buttonBar;
    @FXML
    private JFXButton btnToday;
    @FXML
    private JFXButton btnDashboard;
    @FXML
    private JFXButton btmStudentAttendance;
    @FXML
    private JFXButton btnLogout;
    @FXML
    private AnchorPane attachable;
    
    private final String TodayModule = "src/attendance/gui/view/Today.fxml";
    private final String DashboardModule = "src/attendance/gui/view/Dashboard.fxml";
    private final String StudentModule = "src/attendance/gui/view/StudentAttendance.fxml";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showModule(TodayModule);
    }

    private void showModule(String urlToShow) {
        try {
            File file = new File(urlToShow);
            URL url = file.toURI().toURL();
            attachable.getChildren().clear();
            attachable.getChildren().add(FXMLLoader.load(url));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    private void showToday(ActionEvent event) {
        showModule(TodayModule);

    }

    @FXML
    private void showDashboard(ActionEvent event) {
        showModule(DashboardModule);
    }

    @FXML
    private void showStudentAttendance(ActionEvent event) {
        showModule(StudentModule);

    }

    @FXML
    private void handleLogout(ActionEvent event) {
    }

}
