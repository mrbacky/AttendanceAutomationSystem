package attendance.bll;

import attendance.be.User;
import attendance.dal.DalFacade;
import attendance.dal.DalManager;
import attendance.dal.Mock.MockAttendanceDAO;
import attendance.dal.Mock.MockUserDAO;
import java.time.LocalTime;

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

}
