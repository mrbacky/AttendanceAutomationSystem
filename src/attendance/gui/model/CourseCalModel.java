

package attendance.gui.model;

import attendance.be.CourseCal;
import attendance.be.Student;
import attendance.bll.LogicManager;
import attendance.dal.Mock.MockCourseCalDAO;
import attendance.dal.Mock.MockStudentDAO;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author rado
 */
public class CourseCalModel {

    private static CourseCalModel courseCalModel;
    private final ObservableList<CourseCal> courseCalList = FXCollections.observableArrayList();
    private final MockCourseCalDAO mockCourseCalDAO;
    private LogicManager logicManager;

    public static CourseCalModel getInstance() {
        if (courseCalModel == null) {
            courseCalModel = new CourseCalModel();
        }
        return courseCalModel;
    }

    private CourseCalModel() {
        mockCourseCalDAO = new MockCourseCalDAO();
        logicManager = new LogicManager();
        

    }

    public void loadAllCourseCals() {// calculate absence here
        List<CourseCal> allCourseCals = mockCourseCalDAO.getCourseCals();
        courseCalList.clear();
        courseCalList.addAll(allCourseCals);
    }

    public ObservableList<CourseCal> getObsCourseCals() {
        return courseCalList;
    }
}
