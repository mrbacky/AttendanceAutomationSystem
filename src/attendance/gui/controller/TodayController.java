/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.Attendance;
import attendance.be.AttendanceRecord;
import attendance.be.User;
import attendance.gui.model.AttendanceModel;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class TodayController implements Initializable {

    private Attendance attendance;
    @FXML
    private Label lblUsername;
    @FXML
    private ImageView imgUser;
    @FXML
    private Label lblTodayDate;
    @FXML
    private Label lblTime1;
    @FXML
    private Label lblTime2;
    @FXML
    private Label lblSubject1;
    @FXML
    private Label lblSubject2;
    @FXML
    private JFXToggleButton tglBtn1;
    @FXML
    private JFXToggleButton tglBtn2;
    
    private User user;
   
    private String UsernameLabel;
   
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        showCurrentDate();
        
                tglBtn1.setSelected(false);
                tglBtn2.setSelected(false);
        
        // Toggle Button Present 
        tglBtn1.selectedProperty().addListener(new ChangeListener <Boolean>()
        
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                
                

                if(tglBtn1.isSelected()==true)
                {
                   tglBtn1.setText("Present");
                   AttendanceModel x = AttendanceModel.getInstance();
                   x.markAttendence(user,lblSubject1.getText());
                   
                   tglBtn2.setSelected(false);
                }
                else 
                {
                   tglBtn1.setText("");
                   
                }

            }
            
        });
        
        
         // Toggle Button Absent 
        tglBtn2.selectedProperty().addListener(new ChangeListener <Boolean>()
        
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                if(tglBtn2.isSelected()==true)
                {
                   tglBtn2.setText("Absent");
                   tglBtn1.setSelected(false);

                }
                else 
                {
                   tglBtn2.setText("");
                   

                }

            }
            
        });
       
        
    } 

     void setUser(User currentUser) {
        System.out.println(currentUser.getUsername());
        user = currentUser;
        
        UsernameLabel = User.toString(user.getUsername());
        lblUsername.setText(UsernameLabel);
        
    }
    
 
      public void setTime(AttendanceRecord currentProperties ){
          
        AttendanceRecord atrec = currentProperties;
            
        String TimeLabel = atrec.getTime();
        
        String SubjectLabel = atrec.getSubject();
                
          lblTime1.setText(TimeLabel);
          lblTime2.setText(TimeLabel);
          
         
          
          lblSubject1.setText(SubjectLabel);
          lblSubject2.setText(SubjectLabel);
 }
    
      
      public void showCurrentDate() {
          
          
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      Calendar cal = Calendar.getInstance();
                
      lblTodayDate.setText(dateFormat.format(cal.getTime()));
      
}
    
    
}
