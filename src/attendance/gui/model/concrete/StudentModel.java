package attendance.gui.model.concrete;

import attendance.be.Course;
import attendance.be.Student;
import attendance.bll.util.ConcreteObservable;
import attendance.bll.util.DataObserver;
import attendance.bll.BLLManager;
import attendance.dal.Mock.MockStudentDAO;
import attendance.gui.model.interfaces.IStudentModel;
import java.time.LocalDateTime;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import attendance.bll.IBLLFacade;

/**
 *
 * @author rado
 */
public class StudentModel implements IStudentModel {

    private static StudentModel studentModel;
    private final IBLLFacade bllManager;
    private final ObservableList<Student> studentList = FXCollections.observableArrayList();    //              move to controller

    private final IntegerProperty enrolledStudentsLabel = new SimpleIntegerProperty();
    private final IntegerProperty attendanceCountProperty;
    private ConcreteObservable bllComponent;

    public StudentModel(IBLLFacade bllManager) {
        this.bllManager = bllManager;
        attendanceCountProperty = new SimpleIntegerProperty();
    }

    /**
     *
     * @param course
     * @param current
     */
    @Override
    public void loadAllStudents(Course course, LocalDateTime current) {// calculate absence here
        List<Student> allStudents = bllManager.calculateAbsencePercentage(course, current);
        studentList.clear();
        studentList.addAll(allStudents);
        enrolledStudentsLabel.setValue(allStudents.size());
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<Student> getObsStudents() {
        return studentList;
    }

    /**
     *
     * @return
     */
    @Override

    public ObservableValue<Number> getAttendanceCountProperty() {
        return attendanceCountProperty;
    }

    /**
     *
     * @param c
     */
    @Override
    public void startObserving(Course c) {
        bllComponent = new ConcreteObservable(c);
        System.out.println("startObserving");
        DataObserver observer = (Course c1) -> {
            Platform.runLater(() -> {
                attendanceCountProperty.setValue(bllComponent.getState());
            });
        };
        bllComponent.attach(observer);
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
