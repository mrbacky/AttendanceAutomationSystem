/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.dal;

import attendance.be.User;
import attendance.dal.DAO.AttendanceDAO;
import attendance.dal.DAO.IAttendanceDAO;
import attendance.dal.DAO.IUserDAO;
import attendance.dal.DAO.UserDAO;
import attendance.dal.Mock.MockAttendanceDAO;
import attendance.dal.Mock.MockUserDAO;
import java.time.LocalTime;

/**
 *
 * @author annem
 */
public class DalManager implements DalFacade {
    
    private final MockUserDAO UserDAO;
    private final MockAttendanceDAO mockAttendanceDAO;
    
    private final IUserDAO userDAO;
    private final IAttendanceDAO attendanceDAO;
    
    public DalManager() {
        UserDAO = new MockUserDAO();
        
        userDAO = new UserDAO();
        attendanceDAO = new AttendanceDAO();
        mockAttendanceDAO = new MockAttendanceDAO();
    }
    
    public User auth(String insertedUsername, String password) {
        return UserDAO.auth(insertedUsername, password);
    }
    
    @Override
    public void markAttendance(User currentUser, String currentTask, LocalTime loc) {
        mockAttendanceDAO.markAttendance(currentUser, currentTask, loc);
    }
}
