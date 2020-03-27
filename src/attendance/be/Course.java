/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.be;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author Martin
 */
public class Course {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
//    private final ListProperty<Student> studentList = new SimpleListProperty<>();
    private List<Student> studentList;
    
    public Course(int id, String name) {
        this.id.set(id);
        this.name.set(name);
        this.studentList = studentList;

    }
    
    public Course(int id, String name, LocalDateTime startTime,LocalDateTime endTime) {
        this.id.set(id);
        this.name.set(name);
        this.studentList = studentList;

    }

    
//    public ObservableList getStudentList() {
//        return studentList.get();
//    }
//
//    public void setStudentList(ObservableList value) {
//        studentList.set(value);
//    }
//
//    public ListProperty studentListProperty() {
//        return studentList;
//    }

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
