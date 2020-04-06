package attendance.gui.model;

import attendance.be.Student;
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
    private final ObservableList<Student> studentList = FXCollections.observableArrayList();
    private final MockStudentDAO mockStudentDAO;
    private LogicManager logicManager;
   
    private final ObservableList<Integer> absencePerWeekday = FXCollections.observableArrayList();

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

    public void loadAllStudents(int courseId, LocalDateTime current) {// calculate absence here
        List<Student> allStudents = logicManager.calculateAbsencePercentage(courseId, current);
        studentList.clear();
        studentList.addAll(allStudents);
    }

    public ObservableList<Student> getObsStudents() {
        return studentList;
    }

    public void loadAllWeekdayAbsenceCount(int userId, int courseId){
        List <Integer> lst = logicManager.getWeekdayAbsenceForCourse(userId, courseId);
        absencePerWeekday.clear();
        absencePerWeekday.addAll(lst);
    }
            
    public ObservableList<Integer> getObsWeekdayAbsenceCount(){
        return absencePerWeekday;
    }
}
