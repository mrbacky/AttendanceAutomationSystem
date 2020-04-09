/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.Attendance;
import attendance.be.User;
import attendance.dal.Mock.MockUserDAO;
import attendance.gui.model.ModelCreator;
import attendance.gui.model.ModelException;
import attendance.gui.model.concrete.CourseModel;
import attendance.gui.model.concrete.UserModel;
import attendance.gui.model.interfaces.ICourseModel;
import attendance.gui.model.interfaces.ILessonModel;
import attendance.gui.model.interfaces.IUserModel;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.awt.SystemTray;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author Martin
 */
public class LoginController implements Initializable {

    @FXML
    private Button loginButton;

    private RootStudentController rootStudentController;

    @FXML
    private Label wrongPassword;
    private IUserModel userModel;
    private ICourseModel courseModel;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXPasswordField passwordField;

    private final String ROOT_STUDENT = "/attendance/gui/view/RootStudent.fxml";
    private final String SUBJECT_CHOOSER = "/attendance/gui/view/ChooseSubjectAfterLogin.fxml";

    private User user;

    public LoginController() {
        userModel = ModelCreator.getInstance().getUserModel();
        //  getting new course model from ModelCreator
//        System.out.println("course model from Login contr. " + courseModel);
    }

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        fieldValidator();
    }

    private void showRoot(String ROOT_TO_SHOW) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ROOT_TO_SHOW));
            Parent root = fxmlLoader.load();
            courseModel = ModelCreator.getInstance().getCourseModel();
            if (ROOT_TO_SHOW.equals(ROOT_STUDENT)) {
                RootStudentController controller = fxmlLoader.getController();
                controller.setUser(user);
                controller.injectModel(courseModel);
            }
            if (ROOT_TO_SHOW.equals(SUBJECT_CHOOSER)) {
                ChooseSubjectAfterLoginController controller = fxmlLoader.getController();
                controller.setUser(user);
                controller.injectModel(courseModel);
                controller.initializeComboBox();
            }
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void fieldValidator() {//   could be also decoupled 
        RequiredFieldValidator usernameValidator = new RequiredFieldValidator();
        RequiredFieldValidator passwordValidator = new RequiredFieldValidator();
        usernameField.getValidators().add(usernameValidator);
        usernameValidator.setMessage("Please fill out username.");
        passwordField.getValidators().add(passwordValidator);
        passwordValidator.setMessage("Please fill out password");
        usernameField.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                usernameField.validate();
            }
        });
        passwordField.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                passwordField.validate();
            }
        });
    }

    private void authentification() {
        try {
            this.user = userModel.login(usernameField.getText(), passwordField.getText());
        } catch (ModelException ex) {
            showAlert(ex);
        }
        if (user != null) {
            if (user.getType() == User.UserType.TEACHER) {
                showRoot(SUBJECT_CHOOSER);
                closeLogin();
            } else if (user.getType() == User.UserType.STUDENT) {
                showRoot(ROOT_STUDENT);
                closeLogin();
            }
        } else {
            //showAlert("There is no user.");
        }
    }

    private void showAlert(Exception ex) {
        Alert a = new Alert(Alert.AlertType.ERROR, "An error occured: " + ex.getMessage(), ButtonType.OK);

        a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        a.show();
        //change so whole message shows
        if (a.getResult() == ButtonType.OK) {

        }
    }

    @FXML//   could be also decoupled 
    private void BtnPressed(ActionEvent event) throws IOException {
        authentification();
    }

    @FXML//   could be also decoupled 
    private void EnterPressed(javafx.scene.input.KeyEvent event) throws IOException, InterruptedException {

        if (event.getCode() == KeyCode.ENTER) {
            authentification();
        }
    }

    private void closeLogin() {
        Stage loginStage;
        loginStage = (Stage) loginButton.getScene().getWindow();
        loginStage.close();
    }

}
