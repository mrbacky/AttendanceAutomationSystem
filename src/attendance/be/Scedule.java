/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.be;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author mac
 */
public class Scedule {
    
   /*
    private ObjectProperty<LocalDate> startTime = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> endTime = new SimpleObjectProperty<>();
*/
    private IntegerProperty startTime = new SimpleIntegerProperty();
    private IntegerProperty endTime = new SimpleIntegerProperty();
    private StringProperty subjects = new SimpleStringProperty();
    
   
    
    public Scedule( int startTime, int endTime, String subjects ){
        
        this.startTime.set(startTime);
        this.endTime.set(endTime);
        this.subjects.set(subjects);
    
    
    }
    
    public void setStartTime(int startTime){
    
     this.startTime.set(startTime);
    }
    
    public int getStartTime(){
      return  this.startTime.get();
    }
    
     public IntegerProperty startTimeProperty()
    {
        return startTime;
    }
     public void setEndTime(int startTime){
    
     this.startTime.set(startTime);
    }
    
    public int getEndTime(){
      return  this.startTime.get();
    }
    
     public IntegerProperty endTimeProperty()
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
    
     
}
