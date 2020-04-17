package attendance.dal.DAO.interfaces;

import attendance.be.Course;
import attendance.be.User;
import java.util.List;

/**
 *
 * @author annem
 */
public interface ICourseDAO {

    /**
     * Gets the courses a user is related to.
     *
     * @param user The user.
     * @return The list of courses.
     */
    List<Course> getCourses(User user);

}