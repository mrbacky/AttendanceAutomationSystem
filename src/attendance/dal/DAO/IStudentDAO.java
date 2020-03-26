package attendance.dal.DAO;

import attendance.be.Student;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author annem
 */
public interface IStudentDAO {

    /**
     * Gets the number of lessons conducted in a course.
     *
     * @param courseId The id of the course.
     * @param current The current date and time.
     * @return The number of conducted lessons.
     */
    int getNumberOfConductedLessons(int courseId, LocalDateTime current);

    /**
     * Gets the number of absent lessons for each student for a course.
     *
     * @param courseId The id of the course.
     * @return The list of students with absent lesson count.
     */
    List<Student> getNumberOfAbsentLessons(int courseId);
}
