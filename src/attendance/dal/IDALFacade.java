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
public interface IDALFacade {

    User getUser(String username, String password) throws DalException;

    List<Lesson> getLessonsForToday(int userId, LocalDate current);

    void createRecord(int userId, Lesson lesson);

    List<Course> getCourses(int userId);

    int getNumberOfConductedLessons(Course course, LocalDateTime current);

    List<Student> getNumberOfAbsentLessons(Course course);
    
    boolean hasUpdate(int courseId, LocalDateTime last);

    List<Lesson> getAttendanceRecordsForAllCourses(int userId);
    
    List<Lesson> getAttendanceRecordsForACourse(int userId, int courseId);
    
    LocalDateTime getTimeOfLastUpdate(int courseId, LocalDate current);
    
    int getAttendanceForLesson(int courseId, LocalDateTime current);
}
