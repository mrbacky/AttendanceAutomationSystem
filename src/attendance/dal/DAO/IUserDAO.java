package attendance.dal.DAO;

import attendance.be.Course;
import attendance.be.User;
import java.util.List;

/**
 *
 * @author annem
 */
public interface IUserDAO {

    User getUser(String username, String password);
    
    List<Course> getCourses(int userId);
}
