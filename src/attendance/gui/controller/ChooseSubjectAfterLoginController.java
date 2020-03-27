/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.gui.controller.LoginController;
import attendance.gui.model.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author mega_
 */
public class ChooseSubjectAfterLoginController implements Initializable {

private final String ROOT_TEACHER = "/attendance/gui/view/RootTeacher.fxml";
  
     @FXML
    private Button loginButton;
     private Model model;
    public LoginController loginController;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
