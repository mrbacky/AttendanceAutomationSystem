package attendance.bll;

import attendance.bll.util.LogicException;
import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.User;
import java.time.LocalDate;
import java.util.List;

public interface IBLLFacade {

    /**
     * Gets the user.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The user.
     * @throws LogicException
     */
    User getUser(String username, String password) throws LogicException;

    /**
     * Gets the courses a user is related to.
     *
     * @param user The user.
     * @return The list of courses.
     */
    List<Course> getCourses(User user);

    /**
     * Gets the lessons for the current day of a student.
     *
     * @param student The student.
     * @param current The date of the current day.
     * @return A list of lessons.
     */
    List<Lesson> getLessonsForToday(User student, LocalDate current);

    /**
     * Records the attendance of a lesson for a student.
     *
     * @param student The student.
     * @param lesson The lesson to have attendance recorded.
     */
    void createRecord(User student, Lesson lesson);

    /**
     * Gets the attendance records for all the courses a student is enrolled in.
     *
     * @param student The student.
     * @return A list of attendance records (Lesson objects with status).
     */
    List<Lesson> getAttendanceRecordsForAllCourses(User student);

    /**
     * Gets all the attendance records of a student for a course.
     *
     * @param student The student.
     * @param course The course.
     * @return A list of attendance records (Lesson objects with status).
     */
    List<Lesson> getAttendanceRecordsForACourse(User student, Course course);

    public int calculatePercentage(int absence, int h);

    public int countAbsentLessons(List<Lesson> list);

}
