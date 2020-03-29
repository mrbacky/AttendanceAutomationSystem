/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author mac
 */
public class MockScedule {
    
   /*
    private ObjectProperty<LocalDate> startTime = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> endTime = new SimpleObjectProperty<>();
*/
    private StringProperty startTime = new SimpleStringProperty();
    private StringProperty endTime = new SimpleStringProperty();
    private StringProperty subjects = new SimpleStringProperty();
   
    private StringProperty status = new SimpleStringProperty();
   
    
    
    
    public MockScedule( String startTime, String endTime, String subjects, String status){
        
        this.startTime.set(startTime);
        this.endTime.set(endTime);
        this.subjects.set(subjects);
        this.status.set(status);
    
    }
    
    
    
    public void setStartTime(String startTime){
    
     this.startTime.set(startTime);   
    }
    
    public String getStartTime(){
      return  this.startTime.get();
    }
    
     public StringProperty startTimeProperty()
    {
        return startTime;
    }
     public void setEndTime(String startTime){
    
     this.startTime.set(startTime);
    }
    
    public String getEndTime(){
      return  this.startTime.get();
    }
    
     public StringProperty endTimeProperty()
    {
        return startTime;
    }
     public void setSubjects(String subjects)
    {
        this.subjects.set(subjects);
    }
    
    public String getSubjects()
    {
        return this.subjects.get();
    }
    
    public StringProperty subjectsProperty()
    {
        return subjects;
    }
    
    public void setStatus(String status){
           this.status.set(status);
    }
     
    public String getStatus(){
        
        return this.status.get();
    }
    
    public StringProperty statusProperty(){
         return status;
    }
}
