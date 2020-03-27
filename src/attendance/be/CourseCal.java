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
    private final StringProperty startTime = new SimpleStringProperty();
    private final StringProperty endTime = new SimpleStringProperty();

    //  maybe course could be STRING
    public CourseCal(int id, String courseName, String startTime, String endTime) {
        this.id.set(id);
        this.courseName.set(courseName);
        this.startTime.set(startTime);
        this.endTime.set(endTime);

    }

    public String getEndTime() {
        return endTime.get();
    }

    public void setEndTime(String value) {
        endTime.set(value);
    }

    public StringProperty endTimeProperty() {
        return endTime;
    }

    public String getStartTime() {
        return startTime.get();
    }

    public void setStartTime(String value) {
        startTime.set(value);
    }

    public StringProperty startTimeProperty() {
        return startTime;
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
