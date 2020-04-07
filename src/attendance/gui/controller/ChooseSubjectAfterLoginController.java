/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.be.Course;
import attendance.be.Teacher;
import attendance.be.User;
import attendance.gui.controller.LoginController;
import attendance.gui.model.ModelCreator;
import attendance.gui.model.concrete.CourseModel;
import attendance.gui.model.ModelException;
import attendance.gui.model.concrete.UserModel;
import attendance.gui.model.interfaces.ICourseModel;
import attendance.gui.model.interfaces.IStudentModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mega_
 */
public class ChooseSubjectAfterLoginController implements Initializable {

    @FXML
    private JFXButton loginButton;

    private final String ROOT_TEACHER = "/attendance/gui/view/RootTeacher.fxml";

    public LoginController loginController;
    @FXML
    private JFXComboBox<Course> comboboxS;

    private ICourseModel courseModel;
    private User user;
    private IStudentModel studentModel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCoursesInCombobox();
    }

    public void setUser(User currentUser) {
        this.user = currentUser;
    }

    void injectModel(ICourseModel courseModel) {
        this.courseModel = courseModel;

    }

    public void showRoot(String rootToShow) {
        try {
            URL url = getClass().getResource(rootToShow);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(url);
            Parent root = fxmlLoader.load();

            RootTeacherController controller = (RootTeacherController) fxmlLoader.load();
            studentModel = ModelCreator.getInstance().getStudentModel();
            controller.setUser(user);
            controller.injectModels(courseModel, studentModel);

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void closeLogin() {
        Stage chooseStage;
        chooseStage = (Stage) loginButton.getScene().getWindow();
        chooseStage.close();
    }

    private void loadCoursesInCombobox() {
        comboboxS.getItems().clear();
        comboboxS.getItems().addAll(courseModel.loadAllCourses(user.getId()));
    }

    @FXML
    private void btnTeacherLogin(ActionEvent event) {
        showRoot(ROOT_TEACHER);
        closeLogin();
    }

    @FXML
    private void setSelectedCourse(ActionEvent event) {
        //comboboxS.setSelectionModel(value);
        user.setCurrentSelectedCourse(comboboxS.getSelectionModel().getSelectedIndex());
    }

}
