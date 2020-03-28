/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.be.Requests;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class TeacherChangeRequestController implements Initializable {

    @FXML
    private TableView<Requests> RequestTable;
    @FXML
    private TableColumn<Requests, String> requestcol;
    @FXML
    private Button acceptbtn;
    @FXML
    private Button denybtn;
    @FXML
    private Pane SelectedStudentPane;
    @FXML
    private Label subjectlbl;
    @FXML
    private Label timelbl;
    @FXML
    private Label datelbl;
    @FXML
    private Label Namelbl;
    @FXML
    private Pane messagebox;

     private ObservableList<Requests> StudentRequestsList = FXCollections.observableArrayList();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setReqTable();
    }    
    
    
    
      
    public void setReqTable()
    {
        
        StudentRequestsList.add(new Requests("My Name is Fulll"));
        StudentRequestsList.add(new Requests("My Name is Student 2"));
        StudentRequestsList.add(new Requests("My Name is Student 3"));
        
        
        requestcol.setCellValueFactory(cellData ->cellData.getValue().studentNameProperty());
        
        
                
        RequestTable.setItems(StudentRequestsList);
   
    
    }
}
