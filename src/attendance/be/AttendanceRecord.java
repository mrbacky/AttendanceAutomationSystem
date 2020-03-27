package attendance.be;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//  TEST BE    (DB REPLACEMENT)
public class AttendanceRecord {

    private final StringProperty startTime = new SimpleStringProperty();
    private final StringProperty endTime = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final StringProperty courseName = new SimpleStringProperty();

    public AttendanceRecord(String startTime, String endTime, String courseName, String status) {
        this.startTime.set(startTime);
        this.endTime.set(endTime);
        this.courseName.set(courseName);
        this.status.set(status);
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

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String value) {
        status.set(value);
    }

    public StringProperty statusProperty() {
        return status;
    }

}
