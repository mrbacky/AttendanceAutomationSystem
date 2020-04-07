/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.Attendance;
import attendance.be.User;
import attendance.gui.model.ModelException;
import attendance.gui.model.concrete.UserModel;
import attendance.gui.model.interfaces.ICourseModel;
import attendance.gui.model.interfaces.ILessonModel;
import attendance.gui.model.interfaces.IUserModel;
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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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

    private final String TODAY_MODULE = "/attendance/gui/view/Today.fxml";
//    private final String STUDENT_DASHBOARD_MODULE = "/attendance/gui/view/StudentDashboard.fxml";
    private final String STUDENT_ATTENDANCE_MODULE = "/attendance/gui/view/StudentAttendance.fxml";
    private final String LOGIN_VIEW = "/attendance/gui/view/Login.fxml";

    private LoginController loginController;

    private User user;
    @FXML
    private Label lblHello;
    private IUserModel model;
    private ICourseModel courseModel;
    private ILessonModel lessonModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        showModule(TodayModule);
    }

    public void injectModels(ICourseModel courseModel, ILessonModel lessonModel) {
        this.courseModel = courseModel;
        this.lessonModel = lessonModel;

    }

    public void setUser(User currentUser) {
        this.user = currentUser;
        lblHello.setText(currentUser.getName());
    }
//    void setContr(LoginController loginController) {
//        this.loginController = loginController;
//    }

    private void showModule(String module) {
        try {

            URL url = getClass().getResource(module);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(url);
            fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
            AnchorPane page = (AnchorPane) fxmlLoader.load(url.openStream());

            if (module.equals(TODAY_MODULE)) {
                TodayController controller = (TodayController) fxmlLoader.load();
                controller.setUser(user);
                controller.injectModel(lessonModel);

            }

            if (module.equals(STUDENT_ATTENDANCE_MODULE)) {
                StudentAttendanceController controller = (StudentAttendanceController) fxmlLoader.load();
                controller.setUser(user);
                controller.injectModels(courseModel, lessonModel);
            }

            attachable.getChildren().clear();
            attachable.getChildren().add(page);
            ///name of pane where you want to put the fxml.

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    private void showToday(ActionEvent event) {
        showModule(TODAY_MODULE);

    }

    @FXML
    private void showDashboard(ActionEvent event) {
//        showModule(STUDENT_DASHBOARD_MODULE);
    }

    @FXML
    private void showStudentAttendance(ActionEvent event) {
        showModule(STUDENT_ATTENDANCE_MODULE);

    }

    @FXML
    private void handleLogout(ActionEvent event) throws IOException {

        Stage logOutStage;
        logOutStage = (Stage) btnLogout.getScene().getWindow();
        logOutStage.close();

        URL url = getClass().getResource(LOGIN_VIEW);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

}
