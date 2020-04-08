package attendance.gui.model;

import attendance.be.Course;
import attendance.be.Student;
import attendance.bll.ConcreteObservable;
import attendance.bll.ConcreteObservable2;
import attendance.bll.DataObserver;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author rado
 */
public class StudentModel {

    private static StudentModel studentModel;
    private final ObservableList<Student> studentList = FXCollections.observableArrayList();

    private final IntegerProperty enrolledStudentsLabel;
    private final IntegerProperty attendanceCountProperty;
    private ConcreteObservable bllComponent;
    private ConcreteObservable2 bllComponent2;

    public static StudentModel getInstance() {
        if (studentModel == null) {
            studentModel = new StudentModel();
        }
        return studentModel;
    }

    private StudentModel() {
        enrolledStudentsLabel = new SimpleIntegerProperty();
        attendanceCountProperty = new SimpleIntegerProperty();
    }

    public ObservableList<Student> getObsStudents() {
        return studentList;
    }

    public ObservableValue<Number> getAttendanceCountProperty() {
        return attendanceCountProperty;
    }

    public void startObserving(Course c) {
        bllComponent = new ConcreteObservable(c);
        bllComponent2 = new ConcreteObservable2(c);
        DataObserver observer = new DataObserver() {
            @Override
            public void update(Course c) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        attendanceCountProperty.setValue(bllComponent.getState());
                        List<Student> s = bllComponent2.getState();
                        if (s != null) {
                            studentList.setAll(s);
                            enrolledStudentsLabel.setValue(s.size());
                        }                    
                    }
                });
            }
        };
        bllComponent.attach(observer);
        bllComponent2.attach(observer);
    }

    public int getEnrolledStudentsLabel() {
        return enrolledStudentsLabel.get();
    }

    public void setEnrolledStudentsLabel(int value) {
        enrolledStudentsLabel.set(value);
    }

    public IntegerProperty enrolledStudentsLabelProperty() {
        return enrolledStudentsLabel;
    }
}
