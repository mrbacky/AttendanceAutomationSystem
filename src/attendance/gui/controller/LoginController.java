/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.be.User;
import attendance.dal.Mock.MockUserDAO;
import attendance.gui.model.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Martin
 */
public class LoginController implements Initializable {

    @FXML
    private TextField usernameTxt;
    @FXML
    private TextField passwordTxt;
    @FXML
    private Button loginButton;
    @FXML
    private Label WrongPassword;

    private Model model;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new Model();
    }    
    
    @FXML
    private void BtnPressed(ActionEvent event) {
        User user = model.auth(usernameTxt.getText(), passwordTxt.getText());
        if(user!= null){
            WrongPassword.setVisible(false);
            if(user.getIsTeacher()){
                
            }   
            else{
                
            }
        }
        else{
            WrongPassword.setVisible(true);
        }
    }
    
}
