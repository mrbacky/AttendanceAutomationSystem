package attendance.bll;

import attendance.bll.util.LogicException;
import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.User;
import java.time.LocalDate;
import java.util.List;

public interface IBLLFacade {

    User getUser(String username, String password) throws LogicException;

    List<Course> getCourses(User user);

    List<Lesson> getLessonsForToday(User student, LocalDate current);

    void createRecord(User student, Lesson lesson);

    List<Lesson> getAttendanceRecordsForAllCourses(User student);

    List<Lesson> getAttendanceRecordsForACourse(User student, Course course);

}
