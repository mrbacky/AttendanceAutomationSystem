package attendance.gui.controller;

import attendance.be.Course;
import attendance.be.User;
import attendance.gui.model.interfaces.ICourseModel;
import attendance.gui.model.interfaces.IStudentModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mega_
 */
public class CourseSelectionForTeacherController implements Initializable {

    @FXML
    private JFXButton loginButton;

    private final String ROOT_TEACHER = "/attendance/gui/view/RootTeacher.fxml";

    public LoginController loginController;
    @FXML
    private JFXComboBox<Course> comboboxS;

    private ICourseModel courseModel;
    private IStudentModel studentModel;
    private User user;
    @FXML
    private Label Wronglbl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setUser(User currentUser) {
        this.user = currentUser;
    }

    public void injectModel(ICourseModel courseModel) {
        this.courseModel = courseModel;
    }

    public void initializeComboBox() {
        courseModel.loadAllCourses(user);
        loadCoursesInCombobox();
        Wronglbl.setId("Wronglbl");

    }

    private void showRoot(String rootToShow) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(rootToShow));
            Parent root = fxmlLoader.load();
            RootTeacherController controller = fxmlLoader.getController();
            controller.setUser(user);
            controller.injectModel(courseModel);
            controller.initializeView();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CourseSelectionForTeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void closeLogin() {

        Stage chooseStage;
        chooseStage = (Stage) loginButton.getScene().getWindow();
        chooseStage.close();

    }

    private void loadCoursesInCombobox() {
        comboboxS.getItems().clear();
        comboboxS.getItems().addAll(courseModel.getCourseList());
    }

    @FXML
    private void btnTeacherLogin(ActionEvent event) {
        if (comboboxS != null) {
            showRoot(ROOT_TEACHER);
            closeLogin();
        } else {
            Wronglbl.setText("Please select a course");
        }
        loginButton.pressedProperty();
    }

    @FXML
    private void setSelectedCourse(ActionEvent event) {
        //comboboxS.setSelectionModel(value);
        user.setCurrentSelectedCourse(comboboxS.getSelectionModel().getSelectedIndex());
    }

}
