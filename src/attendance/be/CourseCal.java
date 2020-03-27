package attendance.be;

import java.time.LocalDateTime;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author rado
 */
public class CourseCal {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty courseName = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final ObjectProperty<LocalDateTime> startTime = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDateTime> endTime = new SimpleObjectProperty<>();
    private StatusType statusType;
    

    //  (CourseCal , studentOBJ,  )
    //  maybe course could be STRING
    public CourseCal(int id, String courseName, LocalDateTime startTime, LocalDateTime endTime, StatusType statusType) {
        // courseID?
        this.id.set(id);
        this.courseName.set(courseName);
        this.startTime.set(startTime);
        this.endTime.set(endTime);
        this.statusType = statusType;

    }
    
    public enum StatusType{
       PRESENT,ABSENT
    }

    public LocalDateTime getEndTime() {
        return endTime.get();
    }

    public void setEndTime(LocalDateTime value) {
        endTime.set(value);
    }

    public ObjectProperty endTimeProperty() {
        return endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime.get();
    }

    public void setStartTime(LocalDateTime value) {
        startTime.set(value);
    }

    public ObjectProperty startTimeProperty() {
        return startTime;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String value) {
        status.set(value);
    }

    public StringProperty statusProperty() {
        return status;
    }

    public String getCourseName() {
        return courseName.get();
    }

    public void setCourseName(String value) {
        courseName.set(value);
    }

    public StringProperty courseNameProperty() {
        return courseName;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int value) {
        id.set(value);
    }

    public IntegerProperty idProperty() {
        return id;
    }

}
