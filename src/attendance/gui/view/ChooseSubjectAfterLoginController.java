/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.view;

import attendance.gui.controller.LoginController;
import attendance.gui.model.Model;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
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
    @FXML
    private ComboBox<?> combChooseCourse;
    private final String ROOT_TEACHER = "/attendance/gui/view/RootTeacher.fxml";

    private Model model;
    public LoginController loginController;

    /**
     * Initializes the controller class.
     *
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
}
