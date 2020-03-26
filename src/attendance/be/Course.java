/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.be;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Martin
 */
public class Course {
    
    // add id, change to localdatetime, List<students>
    private final StringProperty name = new SimpleStringProperty();

    private final ObjectProperty<LocalDateTime> startDuration = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDateTime> endDuration = new SimpleObjectProperty<>();

    public Course(String name, LocalDateTime startDuration, LocalDateTime endDuration) {
        this.name.set(name);
        this.startDuration.set(startDuration);
        this.endDuration.set(endDuration);
    }

    public LocalDateTime getEndDuration() {
        return endDuration.get();
    }

    public void setEndDuration(LocalDateTime value) {
        endDuration.set(value);
    }

    public ObjectProperty endDurationProperty() {
        return endDuration;
    }

    public LocalDateTime getStartDuration() {
        return startDuration.get();
    }

    public void setStartDuration(LocalDateTime value) {
        startDuration.set(value);
    }

    public ObjectProperty startDurationProperty() {
        return startDuration;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public StringProperty nameProperty() {
        return name;
    }
    
    @Override
    public String toString() {
        return getName();
    }

}
