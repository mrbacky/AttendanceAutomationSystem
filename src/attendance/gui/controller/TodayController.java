/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.Attendance;
import attendance.be.Scedule;
import attendance.be.User;
import attendance.gui.model.AttendanceModel;
import attendance.gui.model.Model;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.lang.String;

/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class TodayController implements Initializable {
    
    public static final String IN_TODAY_COURSE_VIEW_PATH = "/attendance/gui/view/InTodayCourseView.fxml";

    private Attendance attendance;
    @FXML
    private Label lblUsername;
    @FXML
    private ImageView imgUser;
    @FXML
    private Label lblTodayDate;
    @FXML
    private JFXToggleButton tglBtn1;
   
    private User user;

    private String UsernameLabel;
    
    private Model model;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<Scedule> tableview;
    @FXML
    private TableColumn<Scedule, String> starttimecol;
    @FXML
    private TableColumn<Scedule, String> endtimecol;
    @FXML
    private TableColumn<Scedule, String> subjects;
     @FXML
    private TableColumn<Scedule, String> statuscol;
    
    private ObservableList<Scedule> sceduleList = FXCollections.observableArrayList();
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.model = Model.getInstance();
        setUser();
        initToggleButtons();
        showCurrentDate();
        setTable();
        
        //BINDINGS
        //subjectlbl.textProperty().bind(scedule.subjectsProperty());
      /*
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);
        lblEndDate.textProperty().bind(Bindings.createStringBinding(() ->
               dtf.format(task.getEndDate()), task.endDateProperty()));
        */
        
    }
    
    public void setTable()
    {
        
        sceduleList.add(new Scedule("9:30","11:30", "SCO2", ""));
        sceduleList.add(new Scedule("10:00","14:30", "SDE2", ""));
        sceduleList.add(new Scedule("9:00","15:00", "ITO2",""));
        
        
        starttimecol.setCellValueFactory(cellData ->cellData.getValue().startTimeProperty());
        endtimecol.setCellValueFactory(cellData ->cellData.getValue().endTimeProperty());
        subjects.setCellValueFactory(cellData -> cellData.getValue().subjectsProperty());
        statuscol.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
                
        tableview.setItems(sceduleList);
   
    
    }
   
    /*
    ////////////// This is for deleting 
    
    public void initializeSchedule() throws IOException
    {
        for(Scedule scedule : sceduleList)
        {
            //initialize intodayview
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(IN_TODAY_COURSE_VIEW_PATH));
            Parent root = fxmlLoader.load();
            
            //initialize in today view data to scedule data
            InTodayCourseViewController controller = fxmlLoader.getController();
            controller.setScedule(scedule);
            
            //add in today view to this view
           
         
          
          
         //TilePane tilePane = (TilePane) fxmlLoader.load();
          
          
         anchorPane.getChildren().addAll(root);
          
         anchorPane.setId(IN_TODAY_COURSE_VIEW_PATH);
          
         
          // root.setVisible(true); <- aka Show Yourself. When you want magic to exist, but it doesn't :/
         

        
    }
        
  }
    
    //////////////
*/
    
    public void initToggleButtons() {
        tglBtn1.setSelected(false);
      

        // Toggle Button Present 
        tglBtn1.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (tglBtn1.isSelected() == true) {
                tglBtn1.setText("Present");
            //    AttendanceModel x = AttendanceModel.getInstance();
          //      x.markAttendence(user, lblSubject1.getText());

            } else {
                tglBtn1.setText("Unregistered");

            }
        });
 
    }

    public void showCurrentDate() {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();

        lblTodayDate.setText("Date: " + dateFormat.format(cal.getTime()));

    }

    private void setUser() {

        user = model.getCurrentUser();

        lblUsername.setText("Hello " + user.getName());

//        UsernameLabel = User.toString(user.getUsername());
//        lblUsername.setText(UsernameLabel);
    }

    @FXML
    private void setPresenttglButton(ActionEvent event) {
        
         sceduleList.get(0).setStatus("Present");  //show Present Status
        
         
       
        
    }

  

}
