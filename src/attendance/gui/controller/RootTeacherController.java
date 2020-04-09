/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.be.User;
import attendance.gui.model.ModelCreator;
import attendance.gui.model.interfaces.ICourseModel;
import attendance.gui.model.interfaces.ILessonModel;
import attendance.gui.model.interfaces.IStudentModel;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
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

    private final String DashboardModule = "/attendance/gui/view/TeacherDashboard.fxml";
    private final String LoginPage = "/attendance/gui/view/Login.fxml";

    private User user;

    @FXML
    private Label lblName;
    @FXML
    private JFXButton btnRequests;
    private ICourseModel courseModel;
    private ILessonModel lessonModel;
    private IStudentModel studentModel;
    @FXML
    private BorderPane borderPane;

    /**
     * Initializes the controller class.Jep
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void injectModel(ICourseModel courseModel, ILessonModel lessonModel, IStudentModel studentModel) {
        this.courseModel = courseModel;
        this.lessonModel = lessonModel;
        this.studentModel = studentModel;
    }

    void setUser(User currentUser) {
        this.user = currentUser;
        lblName.setText(user.getName());

    }

    void initializeView() {
        showModule(DashboardModule);
    }

    private void showModule(String MODULE) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MODULE));
            Parent moduleRoot = fxmlLoader.load();

            TeacherDashboardController controller = fxmlLoader.getController();
            controller.setUser(user);
            controller.injectModels(this.courseModel, studentModel, lessonModel);
            controller.initializeView();

            borderPane.setCenter(moduleRoot);
            ///name of pane where you want to put the fxml.
        } catch (IOException ex) {
            Logger.getLogger(RootTeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void showDashboard(ActionEvent event) {
        showModule(DashboardModule);
    }

    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        if (!lessonModel.getObservableLessonList().isEmpty()) {
            lessonModel.stopObserving();
        };

        if (!studentModel.getObservableStudentList().isEmpty()) {
            studentModel.stopObserving();
        };

        Stage logOutStage;
        logOutStage = (Stage) btnLogout.getScene().getWindow();
        logOutStage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(LoginPage));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void showRequests(ActionEvent event) {
    }

}
