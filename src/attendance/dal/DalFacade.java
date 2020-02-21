/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.dal;

import attendance.be.User;

/**
 *
 * @author annem
 */
public interface DalFacade {
    
    User auth(String insertedUsername, String password);
}
