package attendance.gui.model.interfaces;

import attendance.be.Course;
import attendance.be.Student;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

/**
 *
 * @author rado
 */
public interface IStudentModel {

    ObservableList<Student> getStudentList();

    ObservableValue<Number> getAttendanceCountProperty();

    int getEnrolledStudentsLabel();

    void setEnrolledStudentsLabel(int value);

    IntegerProperty enrolledStudentsLabelProperty();

    void startObserving(Course c);

    void stopObserving();

}
