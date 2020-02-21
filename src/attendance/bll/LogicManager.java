package attendance.bll;

import attendance.be.User;
import attendance.dal.Mock.MockUserDAO;

public class LogicManager implements LogicFacade {

    private final MockUserDAO UserDAO;
    
    public LogicManager() {
        UserDAO = new MockUserDAO();
    }

    public User auth(String insertedUsername, String password){
    return UserDAO.auth(insertedUsername, password);
    }
}
