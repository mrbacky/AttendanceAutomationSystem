/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.Attendance;
import attendance.be.User;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class RootStudentController implements Initializable {

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

    private final String TodayModule = "/attendance/gui/view/Today.fxml";
    private final String DashboardModule = "/attendance/gui/view/Dashboard.fxml";
    private final String StudentModule = "/attendance/gui/view/StudentAttendance.fxml";
    private LoginController loginController;
    private final String LoginPage =  "/attendance/gui/view/Login.fxml";


    private User usr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showModule(TodayModule);
    }

//    void setContr(LoginController loginController) {
//        this.loginController = loginController;
//    }
    private void showModule(String urlToShow) {
        try {

            URL url = getClass().getResource(urlToShow);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(url);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            AnchorPane page = (AnchorPane) fxmlLoader.load(url.openStream());

            attachable.getChildren().clear();///name of pane where you want to put the fxml.
            attachable.getChildren().add(page);

            if (urlToShow.equals(TodayModule)) {
                TodayController controller = (TodayController) fxmlLoader.getController();
                controller.setUser(usr);
            } else if (urlToShow.equals(DashboardModule)) {
                DashboardController controller = (DashboardController) fxmlLoader.getController();
                controller.setUser(usr);
            } else {
                StudentAttendanceController controller = (StudentAttendanceController) fxmlLoader.getController();
                controller.setUser(usr);
            }

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
        usr = currentUser;
    }

}
