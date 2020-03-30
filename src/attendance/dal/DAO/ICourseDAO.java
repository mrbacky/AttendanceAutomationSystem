package attendance.dal.DAO;

import attendance.be.Course;
import attendance.be.Lesson;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author annem
 */
public interface ICourseDAO {

    /**
     * Gets the CourseCalendar (course lessons) for the current day of a
     * student.
     *
     * @param userId The userId of the student.
     * @param current The date of the current day.
     * @return A list of lessons.
     */
    List<Lesson> getLessonsForToday(int userId, LocalDate current);

    /**
     * Gets the courses a user is related to.
     *
     * @param userId The id of the user.
     * @return The list of courses.
     */
    List<Course> getCourses(int userId);

    /**
     * Gets the number of lessons conducted in a course.
     *
     * @param courseId The id of the course.
     * @param current The current date and time.
     * @return The number of conducted lessons.
     */
    int getNumberOfConductedLessons(int courseId, LocalDateTime current);
}
