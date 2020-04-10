package attendance.gui.model.interfaces;

import attendance.be.Course;
import attendance.be.User;
import javafx.collections.ObservableList;

/**
 *
 * @author rado
 */
public interface ICourseModel {

    void loadAllCourses(User user);

    ObservableList<Course> getCourseList();

}
