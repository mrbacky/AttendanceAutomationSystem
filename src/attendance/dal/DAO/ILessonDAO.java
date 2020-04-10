package attendance.dal.DAO;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author annem
 */
public interface ILessonDAO {

    /**
     * Gets the CourseCalendar (course lessons) for the current day of a
     * student.
     *
     * @param student The student.
     * @param current The date of the current day.
     * @return A list of lessons.
     */
    List<Lesson> getLessonsForToday(User student, LocalDate current);

    /**
     * Gets the number of lessons conducted in a course.
     *
     * @param course The selected course.
     * @param current The current date and time.
     * @return The number of conducted lessons.
     */
    int getNumberOfConductedLessons(Course course, LocalDateTime current);

}
