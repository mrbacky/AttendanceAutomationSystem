package attendance.bll;

import attendance.bll.util.LogicException;
import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.Student;
import attendance.be.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IBLLFacade {

    User getUser(String username, String password) throws LogicException;

    List<Course> getCourses(int userId);

    List<Lesson> getLessonsForToday(int userId, LocalDate current);

    void createRecord(int userId, Lesson lessonToUpdate);

    List<Lesson> getAttendanceRecordsForAllCourses(int userId);

    List<Lesson> getAttendanceRecordsForACourse(int userId, int courseId);

}
