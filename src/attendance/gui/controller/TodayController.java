/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.Attendance;
import attendance.gui.model.AttendanceModel;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        AttendanceModel attendancerecmodel = new AttendanceModel();
        
     
        
        // Toggle Button Present 
        tglBtn1.selectedProperty().addListener(new ChangeListener <Boolean>()
        
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                if(tglBtn1.isSelected()==true)
                {
                   tglBtn1.setText("Present");
                   tglBtn2.setSelected(false);
                }
                else 
                {
                   tglBtn1.setText("Not Recorded");
                   tglBtn2.setSelected(false);
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
                   tglBtn2.setText("Not Recorded");
                   tglBtn1.setSelected(false);

                }

            }
            
        });
       
        
    } 
    
     
    
    
    
}
