/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.model.interfaces;

import attendance.be.Course;
import attendance.be.Student;
import java.time.LocalDateTime;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

/**
 *
 * @author rado
 */
public interface IStudentModel {

    

    ObservableList<Student> getObservableStudentList();

    ObservableValue<Number> getAttendanceCountProperty();

    void startObserving(Course c);

    int getEnrolledStudentsLabel();

    void setEnrolledStudentsLabel(int value);

    IntegerProperty enrolledStudentsLabelProperty();
    
    void stopObserving();

}
