package attendance.dal;

import attendance.be.Student;
import attendance.be.User;
import attendance.dal.DAO.CourseDAO;
import attendance.dal.DAO.IUserDAO;
import attendance.dal.DAO.UserDAO;
import attendance.dal.Mock.MockAttendanceDAO;
import attendance.dal.Mock.MockUserDAO;
import java.time.LocalTime;
import java.util.List;
import attendance.dal.DAO.ICourseDAO;

/**
 *
 * @author annem
 */
public class DalManager implements DalFacade {

    private final MockUserDAO UserDAO;
    private final MockAttendanceDAO mockAttendanceDAO;

    private final IUserDAO userDAO;
    private final ICourseDAO courseDAO;

    public DalManager() {
        UserDAO = new MockUserDAO();

        userDAO = new UserDAO();
        courseDAO = new CourseDAO();
        mockAttendanceDAO = new MockAttendanceDAO();
    }

//    @Override
//    public User auth(String insertedUsername, String password) {
//        return UserDAO.auth(insertedUsername, password);
//    }
    
    @Override
    public User getUser(String username, String password) throws DalException {
        try {
            return userDAO.getUser(username, password);
        } catch (DalException ex) {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public void markAttendance(User currentUser, String currentTask, LocalTime loc) {
        mockAttendanceDAO.markAttendance(currentUser, currentTask, loc);
    }

    @Override
    public User auth(String insertedUsername, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Student> getAbsentStudents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
