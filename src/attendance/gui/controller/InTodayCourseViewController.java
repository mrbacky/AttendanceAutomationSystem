/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.controller;

import attendance.be.Scedule;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author mega_
 */
public class InTodayCourseViewController implements Initializable {

    @FXML
    private Label subjectlbl;
    @FXML
    private Label starttimelbl;
    @FXML
    private Label endtimelbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void setScedule(Scedule scedule)
    {
        subjectlbl.textProperty().bind(scedule.subjectsProperty());
        //TO DO - bind the rest of the view
        
        /*
          
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);
        lblEndDate.textProperty().bind(Bindings.createStringBinding(() ->
               dtf.format(task.getEndDate()), task.endDateProperty()));
*/
    }
    
}
