package attendance.gui.model.concrete;

import attendance.be.Course;
import attendance.be.User;
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
     * @param user
     */
    @Override
    public void loadAllCourses(User user) {
        List<Course> allCourses = bllManager.getCourses(user);
        courseList.clear();
        courseList.addAll(allCourses);
    }

    @Override
    public ObservableList<Course> getObservableCourseList() {
        return courseList;
    }

}
