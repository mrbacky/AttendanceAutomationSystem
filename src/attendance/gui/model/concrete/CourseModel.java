package attendance.gui.model.concrete;

import attendance.be.Course;
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
    public CourseModel(IBLLFacade bllManager) {
        this.bllManager = bllManager;
    }

    /**
     *
     * @param userId
     */
    @Override
    public List<Course> loadAllCourses(int userId) {
        return bllManager.getCourses(userId);
    }

}
