/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.be.User;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class RootTeacherController implements Initializable {

    @FXML
    private HBox buttonBar;
    @FXML
    private JFXButton btnDashboard;
    @FXML
    private JFXButton btmStudentAttendance;
    @FXML
    private JFXButton btnLogout;
    @FXML
    private AnchorPane attachable;

    private final String DashboardModule = "src/attendance/gui/view/TeacherDashboard.fxml";
    private final String AttendanceModule = "src/attendance/gui/view/TeacherStudentAttendance.fxml";
    private User user;

    /**
     * Initializes the controller class.Jep
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        showModule(DashboardModule);
        if (user != null) {
            String firstName = user.getRealName();
            System.out.println("this is first nameeee   " + firstName);
        }

    }

    public void showModule(String urlToShow) {
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
    private void showDashboard(ActionEvent event) {
        showModule(DashboardModule);
    }

    @FXML
    private void showStudentAttendance(ActionEvent event) {
        showModule(AttendanceModule);

    }

    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        
        
       Stage logOutStage;
        logOutStage = (Stage) btnLogout.getScene().getWindow();
         logOutStage.close();
         
         
         URL url = getClass().getResource(LoginPage);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(url);
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
    }

    void setUser(User currentUser) {
        user = currentUser;
    }

}
