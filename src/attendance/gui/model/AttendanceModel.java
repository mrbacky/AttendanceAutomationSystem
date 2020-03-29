/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.model;

import attendance.be.MockAttendanceRecord;
import attendance.be.User;
import attendance.bll.LogicFacade;
import attendance.bll.LogicManager;
import attendance.dal.Mock.MockAttendanceDAO;
/**
 *
 * @author mac
 */
public class AttendanceModel {

    
    
      private static AttendanceModel single_instance = null; 
   
        private final LogicFacade bllMan;
    // variable of type String 

  
    // private constructor restricted to this class itself 
    private AttendanceModel() {
            bllMan = new LogicManager();
    } 
  
    // static method to create instance of Singleton class 
    public static AttendanceModel getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new AttendanceModel(); 
  
        return single_instance; 
   
}
   
    public void markAttendence(User currentUser ,String currentTask){
      bllMan.markAttendance(currentUser,currentTask);
}
   /*
   private final MockAttendanceDAO AttendanceDAO;
    
    public AttendanceModel() {
         AttendanceDAO = new MockAttendanceDAO();
    }
    
    public MockAttendanceRecord atendancerec(String day,String date,String time ,String subject, String status){
     return AttendanceDAO.atendancerec(day, date, time,subject,status);
   
    
   }
*/
}


    
    
    
    

