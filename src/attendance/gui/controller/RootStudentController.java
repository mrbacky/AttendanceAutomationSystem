/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.Attendance;
import attendance.be.User;
import attendance.gui.model.ModelCreator;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RootStudentController implements Initializable {

    @FXML
    private HBox buttonBar;
    @FXML
    private JFXButton btnToday;
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

    private User user;
    @FXML
    private Label lblHello;
    private ICourseModel courseModel;
    private ILessonModel lessonModel;
    @FXML
    private BorderPane borderPane;

    public RootStudentController() {
        this.lessonModel = ModelCreator.getInstance().getLessonModel();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void injectModel(ICourseModel courseModel) {
        this.courseModel = courseModel;

    }

    public void setUser(User currentUser) {
        this.user = currentUser;
        lblHello.setText(user.getName());
        showModule(TODAY_MODULE);
    }

    private void showModule(String MODULE) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MODULE));
            Parent moduleRoot = fxmlLoader.load();

            if (MODULE.equals(TODAY_MODULE)) {
                TodayController controller = fxmlLoader.getController();
                controller.setUser(user);
                controller.injectModel(lessonModel);
                controller.initializeTodayModule();
            }
            if (MODULE.equals(STUDENT_ATTENDANCE_MODULE)) {
                StudentAttendanceController controller = fxmlLoader.getController();
                controller.setUser(user);
                controller.injectModels(courseModel, lessonModel);
                controller.initializeOverviewModule();
            }
            borderPane.setCenter(moduleRoot);
        } catch (IOException ex) {
            Logger.getLogger(RootStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showToday(ActionEvent event) {
        showModule(TODAY_MODULE);

    }

    private void showDashboard(ActionEvent event) {
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

    @FXML
    private void showStudentAttendance(ActionEvent event) {
        showModule(STUDENT_ATTENDANCE_MODULE);
    }

}
