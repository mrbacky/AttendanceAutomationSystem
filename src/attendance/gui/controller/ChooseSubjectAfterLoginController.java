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
import attendance.gui.model.CourseModel;
import attendance.gui.model.ModelException;
import attendance.gui.model.UserModel;
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
import javafx.scene.control.Label;
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

    private UserModel userModel;
    public LoginController loginController;
    @FXML
    private JFXComboBox<Course> comboboxS;

    private CourseModel courseModel;
    private User user;
    @FXML
    private Label Warninglbl;

    /**
     * Initializes the controller class.
     *
     * @param url
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.courseModel = CourseModel.getInstance();
        userModel = UserModel.getInstance();
        setUser();

        courseModel.loadAllCourses(user.getId());
        loadCoursesInCombobox();
        
        
    }

    private void setUser() {
        try {

            this.user = userModel.getCurrentUser();
        } catch (ModelException ex) {
            Logger.getLogger(ChooseSubjectAfterLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    private void closeLogin() {
          
        Stage chooseStage;
        chooseStage = (Stage) loginButton.getScene().getWindow();
        chooseStage.close();
           
    }

    private void loadCoursesInCombobox() {
        comboboxS.getItems().clear();
        comboboxS.getItems().addAll(courseModel.getObsCourses());
    }

    @FXML
    private void btnTeacherLogin(ActionEvent event) {
          if(comboboxS!=null){
        showRoot(ROOT_TEACHER);
        closeLogin();
    }else{
                Warninglbl.setText("Please select a course");
            }
            loginButton.pressedProperty();
    }

    @FXML
    private void setSelectedCourse(ActionEvent event) {
        //comboboxS.setSelectionModel(value);

        user.setCurrentSelectedCourse(comboboxS.getSelectionModel().getSelectedIndex());
    }

}
