package attendance.gui.model;

import attendance.be.Course;
import attendance.be.Student;
import attendance.bll.LogicFacade;
import attendance.bll.LogicManager;
import attendance.dal.Mock.MockStudentDAO;
import java.time.LocalDateTime;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author rado
 */
public class StudentModel {

    private static StudentModel studentModel;
    private final MockStudentDAO mockStudentDAO;
    private final LogicFacade logicManager;
    private final ObservableList<Student> studentList = FXCollections.observableArrayList();

    public static StudentModel getInstance() {
        if (studentModel == null) {
            studentModel = new StudentModel();
        }
        return studentModel;
    }

    private StudentModel() {
        mockStudentDAO = new MockStudentDAO();
        logicManager = new LogicManager();
        

    }

    public void loadAllStudents(Course course, LocalDateTime current) {// calculate absence here
        List<Student> allStudents = logicManager.calculateAbsencePercentage(course, current);
        studentList.clear();
        studentList.addAll(allStudents);
    }

    public ObservableList<Student> getObsStudents() {
        return studentList;
    }

}
