package attendance.gui.model;

import attendance.be.Course;
import attendance.bll.LogicFacade;
import attendance.bll.LogicManager;
import attendance.dal.Mock.MockCourseDAO;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author rado
 */
public class CourseModel {

    private static CourseModel courseModel;
    private final ObservableList<Course> courseList = FXCollections.observableArrayList();
    private final MockCourseDAO mockCourseDAO;
    private final LogicFacade logicManager;

    public static CourseModel getInstance() {
        if (courseModel == null) {
            courseModel = new CourseModel();
        }
        return courseModel;
    }

    public CourseModel() {
        mockCourseDAO = new MockCourseDAO();
        logicManager = new LogicManager();
    }

    public void loadAllCourses(int userId) {

        List<Course> allCourses = logicManager.getCourses(userId);
        courseList.clear();
        courseList.addAll(allCourses);
    }

    public ObservableList<Course> getObsCourses() {
        return courseList;
    }

}
