package attendance.be;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AttendanceRecord {

    private final StringProperty subject = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final StringProperty time = new SimpleStringProperty();  // change type
    private final StringProperty day = new SimpleStringProperty();
    private final StringProperty date = new SimpleStringProperty();

    public AttendanceRecord(String day,String date,String time ,String subject, String status) {
        setDay(day);
        setDate(date);
        setTime(time);
        setSubject(subject);
        setStatus(status);
    }



    public String getSubject() {
        return subject.get();
    }

    public void setSubject(String value) {
        subject.set(value);
    }

    public StringProperty subjectProperty() {
        return subject;
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

    public String getTime() {
        return time.get();
    }

    public void setTime(String value) {
        time.set(value);
    }

    public StringProperty timeProperty() {
        return time;
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String value) {
        date.set(value);
    }

    public StringProperty dateProperty() {
        return date;
    }

    public String getDay() {
        return day.get();
    }

    public void setDay(String value) {
        day.set(value);
    }

    public StringProperty dayProperty() {
        return day;
    }

}