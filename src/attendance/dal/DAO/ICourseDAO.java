package attendance.dal.DAO;

import attendance.be.CourseCal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author annem
 */
public interface ICourseDAO {

    /**
     * Gets the CourseCalendar (course lessons) for the current day of a student.
     * @param userId The userId of the student.
     * @param current The date of the current day.
     * @return A list of lessons.
     */
    List<CourseCal> getCourseCal(int userId, LocalDate current);

}
