package attendance.gui.model.concrete;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.bll.BLLManager;
import attendance.dal.Mock.MockCourseDAO;
import attendance.gui.model.interfaces.ICourseModel;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import attendance.bll.IBLLFacade;

/**
 *
 * @author rado
 */
public class CourseModel implements ICourseModel {

    private final IBLLFacade bllManager;
    private final ObservableList<Course> courseList = FXCollections.observableArrayList();

    public CourseModel(IBLLFacade bllManager) {
        this.bllManager = bllManager;
    }

    /**
     *
     * @param userId
     */
    @Override
    public void loadAllCourses(int userId) {
        List<Course> allCourses = bllManager.getCourses(userId);
        courseList.clear();
        courseList.addAll(allCourses);
    }

    @Override
    public ObservableList<Course> getObservableCourseList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
