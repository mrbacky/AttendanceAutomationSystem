/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.model;

import attendance.be.User;
import attendance.dal.Mock.MockUserDAO;

/**
 *
 * @author Martin
 */
public class Model {

    private final MockUserDAO UserDAO;

    public Model() {
        UserDAO = new MockUserDAO();
    }

    public User auth(String insertedUsername, String password) {
        
        return UserDAO.auth(insertedUsername, password);
        
    }
}
