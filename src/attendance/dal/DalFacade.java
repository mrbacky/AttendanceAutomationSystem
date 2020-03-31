package attendance.dal;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.Student;
import attendance.be.User;
import java.time.LocalDate;
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

    public List<Course> getCourses(int userId);

    List<Lesson> getLessonsForToday(int userId, LocalDate current);

    public void createRecord(int userId, Lesson lessonToUpdate);
}
