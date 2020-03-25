/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.dal.DAO;

import attendance.be.AttendanceRecord;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author annem
 */
public class StudentDAO implements IStudentDAO {

    private final DBConnectionProvider cp;

    public StudentDAO() {
        cp = new DBConnectionProvider();
    }

    
    public AttendanceRecord createRecord(int userId, int courseCalenderId, String status) {
        try (Connection con = cp.getConnection()) {
            String sql = "INSERT INTO AttendanceRecord(userId, courseCalenderId, status) VALUES (?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, courseCalenderId);
            pstmt.setString(3, status);

            pstmt.executeUpdate();

            AttendanceRecord record = new AttendanceRecord(userId, courseCalenderId, status);

        } catch (Exception e) {
        }

        return null;
    }

     
}
