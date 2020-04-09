package attendance.gui.model.concrete;

import attendance.be.Course;
import attendance.be.Student;
import attendance.bll.observer.DataObserver;
import attendance.bll.observable.ConcreteObservable;
import attendance.bll.observable.ConcreteObservable2;
import attendance.gui.model.interfaces.IStudentModel;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import attendance.bll.IBLLFacade;
import attendance.bll.util.ObserverEvent;

/**
 *
 * @author rado
 */
public class StudentModel implements IStudentModel {

    private final ObservableList<Student> studentList = FXCollections.observableArrayList();

    private final IntegerProperty enrolledStudentsLabel;
    private final IntegerProperty attendanceCountProperty;
    private ConcreteObservable bllComponent;
    private ConcreteObservable2 bllComponent2;

    public StudentModel(IBLLFacade bllManager) {

        attendanceCountProperty = new SimpleIntegerProperty();
        enrolledStudentsLabel = new SimpleIntegerProperty();
    }

    @Override
    public ObservableList<Student> getObservableStudentList() {
        return studentList;
    }

    @Override
    public ObservableValue<Number> getAttendanceCountProperty() {
        return attendanceCountProperty;
    }

    @Override
    public void startObserving(Course c) {
        ObserverEvent e = new ObserverEvent(c);
        bllComponent = new ConcreteObservable(e);
        bllComponent2 = new ConcreteObservable2(e);
        DataObserver observer = new DataObserver() {
            @Override
            public void update(ObserverEvent e) {
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

    @Override
    public void stopObserving() {
        bllComponent.setIsRunning(false);
        bllComponent2.setIsRunning(false);
    }

    @Override
    public int getEnrolledStudentsLabel() {
        return enrolledStudentsLabel.get();
    }

    @Override
    public void setEnrolledStudentsLabel(int value) {
        enrolledStudentsLabel.set(value);
    }

    @Override
    public IntegerProperty enrolledStudentsLabelProperty() {
        return enrolledStudentsLabel;
    }

}
