package attendance.dal.DAO.interfaces;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.Student;
import attendance.be.User;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author annem
 */
public interface IRecordDAO {

    /**
     * Records the attendance of a lesson for a student in the database.
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

    /**
     * Gets the number of absent lessons for each student for a course.
     *
     * @param course The selected course.
     * @return The list of students with an absent lesson count for each
     * student.
     */
    List<Student> getNumberOfAbsentLessons(Course course);

    /**
     * Gets the number of students present for the current lesson.
     *
     * @param course The course of the lesson.
     * @param current The current date and time.
     * @return The number of students present.
     */
    int getAttendanceForLesson(Course course, LocalDateTime current);

    /**
     * Gets the last recorded time for all attendance records of a course in the
     * database.
     *
     * @param course The course.
     * @return The time of the last update.
     */
    LocalDateTime getTimeOfLastUpdate(Course course);

}
