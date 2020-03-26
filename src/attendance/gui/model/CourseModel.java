package attendance.gui.model;

import attendance.be.Course;

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
    private LogicManager logicManager;

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

    public void loadAllCourses() {
        
        List<Course> allCourses = mockCourseDAO.getCourses();
        System.out.println("all coursess from MODEL:   "+allCourses);
        // logicManager.getCourses();
        courseList.clear();
        courseList.addAll(allCourses);
    }

    public ObservableList<Course> getObsCourses() {
        return courseList;
    }

}
