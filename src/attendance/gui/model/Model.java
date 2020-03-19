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

    private final MockUserDAO mockUserDAO;
    private static Model model;
    

    public static Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }
    
    public Model() {
        mockUserDAO = new MockUserDAO();

    }

    public User auth(String insertedUsername, String password) {
        return mockUserDAO.auth(insertedUsername, password);
    }

    public User getCurrentUser() {
        return mockUserDAO.getCurrentUser();
    }

}
