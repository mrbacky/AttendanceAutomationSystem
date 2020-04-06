package attendance.dal;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.Student;
import attendance.be.User;
import attendance.dal.DAO.CourseDAO;
import attendance.dal.DAO.IUserDAO;
import attendance.dal.DAO.UserDAO;
import attendance.dal.Mock.MockAttendanceDAO;
import attendance.dal.Mock.MockUserDAO;
import java.util.List;
import attendance.dal.DAO.ICourseDAO;
import attendance.dal.DAO.IStudentDAO;
import attendance.dal.DAO.StudentDAO;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author annem
 */
public class DalManager implements DalFacade {

    private final MockUserDAO UserDAO;
    private final MockAttendanceDAO mockAttendanceDAO;

    private final IUserDAO userDAO;
    private final ICourseDAO courseDAO;
    private final IStudentDAO studentDAO;

    public DalManager() {
        UserDAO = new MockUserDAO();
        studentDAO = new StudentDAO();
        userDAO = new UserDAO();
        courseDAO = new CourseDAO();
        mockAttendanceDAO = new MockAttendanceDAO();
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
    public List<Lesson> getLessonsForToday(int userId, LocalDate current) {
        return courseDAO.getLessonsForToday(userId, current);
    }

    @Override
    public void createRecord(int userId, Lesson lessonToUpdate) {
        studentDAO.createRecord(userId, lessonToUpdate);
    }

    @Override
    public List<Course> getCourses(int userId) {
        return courseDAO.getCourses(userId);
    }

    @Override
    public int getNumberOfConductedLessons(Course course, LocalDateTime current) {
        return courseDAO.getNumberOfConductedLessons(course, current);
    }

    @Override
    public List<Student> getNumberOfAbsentLessons(Course course) {
        return studentDAO.getNumberOfAbsentLessons(course);
    }
    
    @Override
    public boolean hasUpdate(int courseId, LocalDateTime last) 
    {
        LocalDateTime ld = courseDAO.getTimeOfLastUpdate(courseId, LocalDate.now());        
        boolean hasUpdate = last.isBefore(ld);
        return hasUpdate;
    }
}
