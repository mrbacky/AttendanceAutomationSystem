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
public class Model {

    private final MockUserDAO mockUserDAO;
    private static Model model;
    private User currentUser;

    private final LogicFacade logicManager;

    /**
     * Create instance of Singleton.
     *
     * @return
     */
    public static Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    private Model() {
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
