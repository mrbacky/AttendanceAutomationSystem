/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.be.Course;
import attendance.be.User;
import attendance.gui.controller.LoginController;
import attendance.gui.model.CourseModel;
import attendance.gui.model.UserModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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

    private UserModel model;
    public LoginController loginController;
    @FXML
    private JFXComboBox<Course> comboboxS;
    private CourseModel courseModel;
    private UserModel userModel;
     private User user;
    /**
     * Initializes the controller class.
     *
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.userModel = UserModel.getInstance();
        this.courseModel = CourseModel.getInstance();
        
        setUser();
        
       // System.out.println("Course: " + courseModel.getObsCourses());
        
        coursesInCB();
    }

    @FXML
    private void BtnPressed(ActionEvent event) {
        showRoot(ROOT_TEACHER);
        closeLogin();
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

    private void coursesInCB() {
        
        Course course = comboboxS.getSelectionModel().getSelectedItem();
        
      //JFXComboBox combobox = new JFXComboBox();
      //combobox.getItems().setAll(comboboxS);
       comboboxS.getItems().setAll(courseModel.getObsCourses());
       
       
       
       List<Course>courseList = courseModel.getObsCourses();
     //  courseList.forEach((course) -> {
     //      comboboxS.getSelectionModel().select(course);
     //   });
        //comboboxS.getSelectionModel().select(1); 
    
      comboboxS.getItems().setAll(courseModel.getObsCourses());
      
     
    }

    private void setUser() {
        
        user = model.getCurrentUser();
    }
    
}
