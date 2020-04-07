package attendance.bll;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.Student;
import attendance.be.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface LogicFacade {

    User getUser(String username, String password) throws LogicException;

    List<Student> getAbsentStudents();

    List<Course> getCourses(int userId);

    List<Lesson> getLessonsForToday(int userId, LocalDate current);

    void createRecord(int userId, Lesson lessonToUpdate);

    List<Student> calculateAbsencePercentage(Course course, LocalDateTime current);

    int studentsEnrolledInCourse();

    List<Lesson> getAttendanceRecordsForAllCourses(int userId);

    List<Lesson> getAttendanceRecordsForACourse(int userId, int courseId);

    

}
