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
    
    @FXML
    private CourseModel courseModel;
    private User user;

    /**
     * Initializes the controller class.
     *
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        this.courseModel = CourseModel.getInstance();
        this.userModel = UserModel.getInstance();
        setUser();
        
        courseModel.loadAllCourses(user.getId());
        System.out.println("courses of user: " + courseModel.getObsCourses());
        loadCoursesInCombobox();
        
    }

    private void setUser() {
        try {
            this.user = userModel.getCurrentUser();
        } catch (ModelException ex) {
            Logger.getLogger(TodayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        cbChooseCourse.getItems().clear();
        cbChooseCourse.getItems().addAll(courseModel.getObsCourses());    }

    private void coursesInCB() {

//        Course course = comboboxS.getSelectionModel().getSelectedItem();

        //JFXComboBox combobox = new JFXComboBox();
        //combobox.getItems().setAll(comboboxS);
        List<Course> courseList = courseModel.getObsCourses();
        comboboxS.getItems().setAll(courseList);

        
        //  courseList.forEach((course) -> {
        //      comboboxS.getSelectionModel().select(course);
        //   });
        //comboboxS.getSelectionModel().select(1); 

        

    }




    @FXML
    private void btnTeacherLogin(ActionEvent event) {
        showRoot(ROOT_TEACHER);
        closeLogin();
    }

}
