package attendance.dal;

import attendance.be.Student;
import attendance.be.User;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author annem
 */
public interface DalFacade {

    User auth(String insertedUsername, String password);

    User getUser(String username, String password) throws DalException;

    void markAttendance(User currentUser, String currentTask, LocalTime loc);
    
    List<Student> getAbsentStudents();
}
