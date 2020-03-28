/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.be;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author mac
 */
public class Requests {
    
 private StringProperty studentName = new SimpleStringProperty();    
    
 
   public Requests(String studentName){
    
      
      this.studentName.set(studentName);
}

    public String getStudentName() {
        return this.studentName.get();
    }

    public void setStudentName(String studentName) {
        
         this.studentName.set(studentName);
    }
   
   public StringProperty studentNameProperty()
    {
        return studentName;
    }
    
    
}

