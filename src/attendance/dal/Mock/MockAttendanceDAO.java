/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.dal.Mock;

import attendance.be.User;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class MockAttendanceDAO {

    HashMap<String, User> markedList = new HashMap<String, User>();  //Subject, Student 

    public void markAttendance(User currentUser, String currentTask, LocalTime loc) {
        markedList.put(currentTask, currentUser);
        System.out.println(markedList);

    }

}
