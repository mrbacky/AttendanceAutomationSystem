/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.model;

import attendance.be.User;
import attendance.bll.LogicFacade;
import attendance.bll.LogicManager;
import attendance.dal.Mock.MockUserDAO;

/**
 *
 * @author Martin
 */
public class UserModel {

    private final MockUserDAO mockUserDAO;
    private static UserModel model;
    private User currentUser;

    private final LogicFacade logicManager;

    /**
     * Create instance of Singleton.
     *
     * @return
     */
    public static UserModel getInstance() {
        if (model == null) {
            model = new UserModel();
        }
        return model;
    }

    private UserModel() {
        logicManager = new LogicManager();
        mockUserDAO = new MockUserDAO();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public User login(String username, String password) {
        currentUser = logicManager.getUser(username, password);
        return logicManager.getUser(username, password);
    }
}
