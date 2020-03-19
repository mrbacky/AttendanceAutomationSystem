package attendance.dal.DAO;

import attendance.be.AttendanceRecord;
import attendance.be.Course;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author annem
 */
public class AttendanceDAO implements IAttendanceDAO {

    private final DBConnectionProvider cp;
    

    /**
     * Constructor, which creates the connection with the database.
     */
    public AttendanceDAO() {
        cp = new DBConnectionProvider();
        
    }

    /*
    @Override
    public AttendanceRecord createRecord(String day, String date, String time, String subject, String status) {
        try (Connection con = cp.getConnection()) {
            String sql = "INSERT INTO RecordList(day, date, time, subject, status) VALUES (?,?,?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, day);
            pstmt.setString(2, date);
            pstmt.setString(3, time);
            pstmt.setString(4, subject);            // unsure about the table
            pstmt.setString(5, status);
            pstmt.executeUpdate();

            AttendanceRecord record = new AttendanceRecord(day, date, time, subject, status);

        } catch (Exception e) {
        }

        return null;
    }
*/
    
    
    public ArrayList<Course> getCourse() throws SQLServerException{
        ArrayList<Course> courses = new ArrayList();
        String sql = "SELECT * FROM CourseCalender,Course JOIN Course ON CourseCalender.id = Course.id";
        try(Connection con = cp.getConnection()){
            PreparedStatement ptst = con.prepareStatement(sql);
            ResultSet rs = ptst.executeQuery();
            while(rs.next()){
                Course c = new Course(null,null,null);
                c.setName(rs.getString("name"));
                c.setStartDuration(rs.getTime("startTime").toString());
                c.setEndDuration(rs.getTime("endTime").toString());
                courses.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return courses;
    }
    
    

}
