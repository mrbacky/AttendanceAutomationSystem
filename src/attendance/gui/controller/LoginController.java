/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.Attendance;
import attendance.be.User;
import attendance.dal.Mock.MockUserDAO;
import attendance.gui.model.UserModel;
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

/**
 * FXML Controller class
 *
 * @author Martin
 */
public class LoginController implements Initializable {

    @FXML
    private Button loginButton;

    private RootStudentController rootStudentController;

    public LoginController loginController;

    private User currentUser;
    @FXML
    private Label wrongPassword;
    private UserModel model;
    private Attendance attendance;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXPasswordField passwordField;

    private final String ROOT_STUDENT = "/attendance/gui/view/RootStudent.fxml";
//    private final String SUBJECT_CHOOSER = "/attendance/gui/view/ChooseSubjectAfterLogin.fxml";
    private final String ROOT_TEACHER = "/attendance/gui/view/RootTeacher.fxml";

    private User user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.model = UserModel.getInstance();

        fieldValidator();

    }

    public void setMainApp(Attendance attendance) {
        this.attendance = attendance;
    }

    public void showRoot(String rootToShow) {
        try {
            URL url = getClass().getResource(rootToShow);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(url);
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println(e);
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
        user = model.login(usernameField.getText(), passwordField.getText());
        if (user != null) {
            if (user.getType() == User.UserType.TEACHER) {
//                showRoot(SUBJECT_CHOOSER);
                showRoot(ROOT_TEACHER);
                closeLogin();
            } else if (user.getType() == User.UserType.STUDENT) {
                showRoot(ROOT_STUDENT);
                closeLogin();
            }
        } else {
            wrongPassword.setVisible(true);
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
