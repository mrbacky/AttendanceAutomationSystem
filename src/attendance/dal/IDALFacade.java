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

    List<Course> getCourses(User user);

    List<Lesson> getLessonsForToday(User student, LocalDate current);

    void createRecord(User student, Lesson lesson);

    List<Lesson> getAttendanceRecordsForAllCourses(User student);

    List<Lesson> getAttendanceRecordsForACourse(User student, Course course);

    int getNumberOfConductedLessons(Course course, LocalDateTime current);

    List<Student> getNumberOfAbsentLessons(Course course);

    int getAttendanceForLesson(Course course, LocalDateTime current);

    LocalDateTime getTimeOfLastUpdate(Course course, LocalDate current);

    boolean hasUpdate(Course course, LocalDateTime last);

}
