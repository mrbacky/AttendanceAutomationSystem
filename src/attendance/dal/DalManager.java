/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.dal;

import attendance.be.Course;
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
    public User getUser(String username, String password) {
        return userDAO.getUser(username, password);
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

    @Override
    public List<Course> getCourses(int userId) {
        return courseDAO.getCourses(userId);
    }
    
    

}
