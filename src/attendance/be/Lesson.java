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
public class Lesson {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty courseName = new SimpleStringProperty();
    private final ObjectProperty<LocalDateTime> startTime = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDateTime> endTime = new SimpleObjectProperty<>();
    private StatusType statusType;

    public Lesson(int id, String courseName, LocalDateTime startTime, LocalDateTime endTime, StatusType statusType) {
        // courseID?
        this.id.set(id);
        this.courseName.set(courseName);
        this.startTime.set(startTime);
        this.endTime.set(endTime);
        this.statusType = statusType;

    }

    @Override
    public String toString() {
//        return "CourseCal{" + "courseName=" + courseName + ", startTime=" + startTime + ", endTime=" + endTime + '}';
        String output = formatTime(startTime.get()) + " - " + formatTime(endTime.get()) + " " + courseName.get();
        return output;
    }

    private String formatTime(LocalDateTime timeToProcess) {
        return "" + timeToProcess.getHour() + ":" + checkIfNot10(timeToProcess.getMinute());
    }

    private String checkIfNot10(int time) {
        String timeStringas = "" + time;
        if (time < 10) {
            timeStringas = "0" + timeStringas;
        }
        return timeStringas;
    }

    public enum StatusType {
        PRESENT, ABSENT, UNREGISTERED
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
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
