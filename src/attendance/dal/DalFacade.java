/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.dal;

import attendance.be.User;
import java.time.LocalTime;

/**
 *
 * @author annem
 */
public interface DalFacade {
    
    User auth(String insertedUsername, String password);

    public void markAttendance(User currentUser, String currentTask, LocalTime loc);
}
