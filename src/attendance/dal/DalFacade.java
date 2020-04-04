package attendance.dal;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.Student;
import attendance.be.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author annem
 */
public interface DalFacade {

    User getUser(String username, String password) throws DalException;

    List<Lesson> getLessonsForToday(int userId, LocalDate current);

    public void createRecord(int userId, Lesson lesson);

    public List<Course> getCourses(int userId);

    int getNumberOfConductedLessons(Course course, LocalDateTime current);

    List<Student> getNumberOfAbsentLessons(Course course);

}
