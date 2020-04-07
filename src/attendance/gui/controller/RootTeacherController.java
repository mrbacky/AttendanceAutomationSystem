/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.be.User;
import attendance.gui.model.ModelException;
import attendance.gui.model.concrete.UserModel;
import attendance.gui.model.interfaces.ICourseModel;
import attendance.gui.model.interfaces.IStudentModel;
import attendance.gui.model.interfaces.IUserModel;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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

    private final String DashboardModule = "/attendance/gui/view/TeacherDashboard.fxml";
    private final String LoginPage = "/attendance/gui/view/Login.fxml";

    private User user;

    @FXML
    private Label lblName;
    @FXML
    private JFXButton btnRequests;
    private ICourseModel courseModel;
    private IStudentModel studentModel;

    /**
     * Initializes the controller class.Jep
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void injectModels(ICourseModel courseModel, IStudentModel studentModel) {
        this.courseModel = courseModel;
        this.studentModel = studentModel;

    }

    void setUser(User currentUser) {
        this.user = currentUser;
        lblName.setText(user.getName());
        showModule(DashboardModule);

    }

    private void showModule(String urlToShow) {
        try {

            URL url = getClass().getResource(urlToShow);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(url);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            AnchorPane page = (AnchorPane) fxmlLoader.load(url.openStream());

            TeacherDashboardController controller = (TeacherDashboardController) fxmlLoader.load();
            controller.setUser(user);
            controller.injectModels(courseModel, studentModel);

            attachable.getChildren().clear();
            attachable.getChildren().add(page);
            ///name of pane where you want to put the fxml.

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    private void showDashboard(ActionEvent event) {
        showModule(DashboardModule);
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

}
