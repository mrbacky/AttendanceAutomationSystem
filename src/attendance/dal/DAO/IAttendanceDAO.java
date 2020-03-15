package attendance.dal.DAO;

import attendance.be.AttendanceRecord;

/**
 *
 * @author annem
 */
public interface IAttendanceDAO {
    
    /**
     *  Inserts data of student attendance record into DB
     *  @return AttendanceRecord object 
     */
    AttendanceRecord createRecord(String day, String date, String time, String subject, String status);
    
}
