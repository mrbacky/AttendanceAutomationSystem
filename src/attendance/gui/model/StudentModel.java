package attendance.gui.model;

import attendance.be.Course;
import attendance.be.Student;
import attendance.bll.ConcreteObservable;
import attendance.bll.DataObserver;
import attendance.bll.LogicFacade;
import attendance.bll.LogicManager;
import attendance.dal.Mock.MockStudentDAO;
import java.time.LocalDateTime;
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
    private final MockStudentDAO mockStudentDAO;
    private final LogicFacade logicManager;
    
    private final IntegerProperty enrolledStudentsLabel = new SimpleIntegerProperty();   
    private final IntegerProperty attendanceCountProperty;
    private ConcreteObservable bllComponent;

    public static StudentModel getInstance() {
        if (studentModel == null) {
            studentModel = new StudentModel();
        }
        return studentModel;
    }

    private StudentModel() {
        mockStudentDAO = new MockStudentDAO();
        logicManager = new LogicManager();        
        attendanceCountProperty = new SimpleIntegerProperty();
    }

    public void loadAllStudents(int courseId, LocalDateTime current) {// calculate absence here
        List<Student> allStudents = logicManager.calculateAbsencePercentage(courseId, current);
        studentList.clear();
        studentList.addAll(allStudents);
        enrolledStudentsLabel.setValue(allStudents.size());
        System.out.println("loadAllStudents: " + enrolledStudentsLabel.getValue());
    }

    public ObservableList<Student> getObsStudents() {
        return studentList;
    }

    public ObservableValue<Number> getAttendanceCountProperty() {
        return attendanceCountProperty;
    }

    public void startObserving(Course c) {
        bllComponent = new ConcreteObservable(c);
        System.out.println("here");
        DataObserver observer = new DataObserver() {
            @Override
            public void update(Course c) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("obs " + attendanceCountProperty.toString());
                        attendanceCountProperty.setValue(bllComponent.getState());
                        
                    }
                });
            }
        };
        bllComponent.attach(observer);
    }
    ////////////////////////////////////////////////////////////////////////////
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
