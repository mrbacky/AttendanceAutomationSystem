package attendance.bll;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.Student;
import attendance.be.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface LogicFacade {

    User auth(String insertedUsername, String password);

    User getUser(String username, String password) throws LogicException;

    public void markAttendance(User currentUser, String currentTask);

    List<Student> getAbsentStudents();

    List<Course> getCourses(int userId);

    List<Lesson> getLessonsForToday(int userId, LocalDate current);

    void createRecord(int userId, Lesson lessonToUpdate);

    List<Student> calculateAbsencePercentage(int courseId, LocalDateTime current);

}
