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
 * @author Martin
 */
public class Course {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty courseName = new SimpleStringProperty();

    public Course(int id, String courseName) {
        this.id.set(id);
        this.courseName.set(courseName);

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

  
    @Override
    public String toString() {
        return  "" + courseName.get();
       // String output = "" + courseName.get();
       // return output;
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
