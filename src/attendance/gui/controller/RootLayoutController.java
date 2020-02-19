/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.Attendance;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class RootLayoutController implements Initializable {

    private Attendance attendance;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    attendance = new Attendance();
        
    }

    private void showScene(Parent root) {
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showToday(ActionEvent event) {
    }

    @FXML
    private void showDashboard(ActionEvent event) throws IOException {
        Parent root;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/attendance/gui/view/Dashboard.fxml"));
        root = (Parent) fxmlLoader.load();
        //attendance.
        showScene(root);

    }

    @FXML
    private void showStudentAttendance(ActionEvent event) {
    }

    @FXML
    private void handleLogout(ActionEvent event) {
    }

}
