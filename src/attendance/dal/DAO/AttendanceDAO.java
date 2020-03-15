package attendance.dal.DAO;

import attendance.be.AttendanceRecord;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author annem
 */
public class AttendanceDAO implements IAttendanceDAO {

    private final DBConnectionProvider cp;
    private IAttendanceDAO attendanceDAO;

    /**
     * Constructor, which creates the connection with the database.
     */
    public AttendanceDAO() {
        cp = new DBConnectionProvider();
        attendanceDAO = new AttendanceDAO();
    }

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

}
