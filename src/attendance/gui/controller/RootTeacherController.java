/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.be.User;
import attendance.gui.model.Model;
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
import javafx.scene.control.Label;
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
 //   @FXML
 //   private JFXButton btmStudentAttendance;
    @FXML
    private JFXButton btnLogout;
    @FXML
    private AnchorPane attachable;

    private final String DashboardModule = "src/attendance/gui/view/TeacherStudentAttendance.fxml";
  //  private final String AttendanceModule = "src/attendance/gui/view/TeacherStudentAttendance.fxml";
    private final String LoginPage = "/attendance/gui/view/Login.fxml";
    private final String Request = "src/attendance/gui/view/TeacherChangeRequest.fxml";
    
    private User user;
    private Model model;
    @FXML
    private Label lblName;
    @FXML
    private JFXButton btnRequests;

    /**
     * Initializes the controller class.Jep
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.model = Model.getInstance();
        setUser();
        
        

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
    

 /*   @FXML
    private void showStudentAttendance(ActionEvent event) {
        showModule(AttendanceModule);

    }*/

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

    void setUser() {
        user = model.getCurrentUser();
        showModule(DashboardModule);
        lblName.setText(user.getName());
    }

    @FXML
    private void showRequests(ActionEvent event) {
         showModule(Request);
       
        
    }

}
