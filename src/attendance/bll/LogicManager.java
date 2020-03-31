package attendance.bll;

import attendance.be.Course;
import attendance.be.Student;
import attendance.be.User;
import attendance.dal.DalException;
import attendance.dal.DalFacade;
import attendance.dal.DalManager;
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
    public User getUser(String username, String password) throws LogicException {
        try {
            //hash password here. create a tool in a utility folder and call method from there.
            return dalFacade.getUser(username, password);
        } catch (DalException ex) {
            throw new LogicException(ex.getMessage());
        }
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
    public double calculateAbsence(Course selectedCourse, int lessonsAttended, int lessonsToDate, LocalDate currentDay) {
        //          Presence % = lessons attended / lessons until today * 100
        //          Absence % = 100 - Presence%
        return 88.14;
    }

}
