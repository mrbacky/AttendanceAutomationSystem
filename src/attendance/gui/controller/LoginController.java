/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.Attendance;
import attendance.be.User;
import attendance.dal.Mock.MockUserDAO;
import attendance.gui.model.Model;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

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
    private Model model;
    private Attendance attendance;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXPasswordField passwordField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new Model();
        fieldValidator();
    }

    public void setMainApp(Attendance attendance) {
        this.attendance = attendance;
    }

    public void showStudentRoot() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Attendance.class.getResource("/attendance/gui/view/RootStudent.fxml"));
        Parent user = loader.load();

        // Show the scene containing the root layout.
        //Scene rootLayoutScene = new Scene(rootLayout);
        RootStudentController controller = (RootStudentController) loader.getController();

        controller.setUser(currentUser);
        Stage stage = new Stage();
        Scene scene = new Scene(user);
        stage.setScene(scene);
        stage.show();

    }

    public void showTeacherRoot() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Attendance.class.getResource("/attendance/gui/view/RootTeacher.fxml"));
        Parent user = loader.load();

        // Show the scene containing the root layout.
        //Scene rootLayoutScene = new Scene(rootLayout);
        Stage stage = new Stage();
        Scene scene = new Scene(user);
        stage.setScene(scene);
        stage.show();

    }

    private void fieldValidator() {
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

    @FXML

    private void BtnPressed(ActionEvent event) throws IOException {

        currentUser = model.auth(usernameField.getText(), passwordField.getText());
        if (currentUser != null) {
            //WrongPassword.setVisible(false);
            if (currentUser.getIsTeacher()) {
                showTeacherRoot();
//                attendance.closeStage();
            } else {
                showStudentRoot();
//                attendance.closeStage();

            }
        } else {
            wrongPassword.setVisible(true);
        }
    }

    @FXML
    private void EnterPressed(javafx.scene.input.KeyEvent event) throws IOException, InterruptedException {

        if (event.getCode() == KeyCode.ENTER) {
            currentUser = model.auth(usernameField.getText(), passwordField.getText());
            if (currentUser != null) {
                //WrongPassword.setVisible(false);
                if (currentUser.getIsTeacher()) {
                    showTeacherRoot();
//                    attendance.closeStage();
                } else {
                    showStudentRoot();
//                    attendance.closeStage();

                }
            } else {
                wrongPassword.setVisible(true);

            }

        }

    }
}
