package attendance.dal;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.Student;
import attendance.be.User;
import attendance.dal.DAO.AttendanceRecordDAO;
import attendance.dal.DAO.CourseDAO;
import attendance.dal.DAO.IAttendanceRecordDAO;
import attendance.dal.DAO.ICourseDAO;
import attendance.dal.DAO.ILessonDAO;
import attendance.dal.DAO.IUserDAO;
import attendance.dal.DAO.LessonDAO;
import attendance.dal.DAO.UserDAO;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author annem
 */
public class DALManager implements IDALFacade {

    private final IAttendanceRecordDAO attendanceDAO;
    private final ICourseDAO courseDAO;
    private final ILessonDAO lessonDAO;
    private final IUserDAO userDAO;

    public DALManager() {
        attendanceDAO = new AttendanceRecordDAO();
        lessonDAO = new LessonDAO();
        courseDAO = new CourseDAO();
        userDAO = new UserDAO();
    }

    @Override
    public User getUser(String username, String password) throws DalException {
        try {
            return userDAO.getUser(username, password);
        } catch (DalException ex) {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public List<Course> getCourses(User user) {
        return courseDAO.getCourses(user);
    }

    @Override
    public List<Lesson> getLessonsForToday(User student, LocalDate current) {
        return lessonDAO.getLessonsForToday(student, current);
    }

    @Override
    public void createRecord(User student, Lesson lesson) {
        attendanceDAO.createRecord(student, lesson);
    }

    @Override
    public List<Lesson> getAttendanceRecordsForAllCourses(User student) {
        return attendanceDAO.getAttendanceRecordsForAllCourses(student);
    }

    @Override
    public List<Lesson> getAttendanceRecordsForACourse(User student, Course course) {
        return attendanceDAO.getAttendanceRecordsForACourse(student, course);
    }

    @Override
    public int getNumberOfConductedLessons(Course course, LocalDateTime current) {
        return lessonDAO.getNumberOfConductedLessons(course, current);
    }

    @Override
    public List<Student> getNumberOfAbsentLessons(Course course) {
        return attendanceDAO.getNumberOfAbsentLessons(course);
    }

    @Override
    public int getAttendanceForLesson(Course course, LocalDateTime current) {
        return attendanceDAO.getAttendanceForLesson(course, current);
    }

    @Override
    public LocalDateTime getTimeOfLastUpdate(Course course) {
        return attendanceDAO.getTimeOfLastUpdate(course);
    }

    @Override
    public boolean hasUpdate(Course course, LocalDateTime lastReceivedUpdate) {
        if (course.getId() != 0 && lastReceivedUpdate != null) {
            LocalDateTime databaseUpdate = attendanceDAO.getTimeOfLastUpdate(course);
            boolean hasUpdate = lastReceivedUpdate.isBefore(databaseUpdate);
            return hasUpdate;
        }
        return false;
    }

}