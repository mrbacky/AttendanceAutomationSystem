package attendance.bll;

import attendance.be.Course;
import attendance.be.Student;
import attendance.be.User;
import attendance.dal.DalFacade;
import attendance.dal.DalManager;
import attendance.dal.Mock.MockAttendanceDAO;
import attendance.dal.Mock.MockUserDAO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class LogicManager implements LogicFacade {

    private final DalFacade dalFacade;

    public LogicManager() {
        dalFacade = new DalManager();
    }

    public User auth(String insertedUsername, String password) {
        return dalFacade.auth(insertedUsername, password);
    }

    @Override
    public User getUser(String username, String password) {
        //hash password here. create a tool in a utility folder and call method from there.
        return dalFacade.getUser(username, password);        
    }

    @Override
    public void markAttendance(User currentUser, String currentTask) {
        LocalTime loc = java.time.LocalTime.now();
        dalFacade.markAttendance(currentUser, currentTask, loc);
    }

    @Override
    public List<Student> getAbsentStudents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double calculateAbsence(Course selectedCourse,int lessonsAttended, int lessonsToDate, LocalDate currentDay) {
        //          Presence % = lessons attended / lessons until today * 100
        //          Absence % = 100 - Presence%
        return 88.14;
    }


    @Override
    public List<Course> getCourses(int userId) {
        return dalFacade.getCourses(userId);
    }
    
/*
    @Override
    public List<Course> getCoursesForTeacher(Course selectedCourse, int userId) {
        return dalFacade.getCoursesForTeacher(userId,selectedCourse);
    }
*/

}
