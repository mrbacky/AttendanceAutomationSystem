package attendance.bll;

import attendance.be.User;

public interface LogicFacade {

    User auth(String insertedUsername, String password);

    User getUser(String username, String password);

    public void markAttendance(User currentUser, String currentTask);

    

}
