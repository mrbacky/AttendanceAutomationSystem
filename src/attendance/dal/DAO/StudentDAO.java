/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.dal.DAO;

import attendance.be.CourseCal;
import attendance.be.Student;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author annem
 */
public class StudentDAO implements IStudentDAO {

    private final DBConnectionProvider connection;

    public StudentDAO() {
        connection = new DBConnectionProvider();
    }

    //public List<Student> getNumberOfAbsentLessons(Course course) {
    @Override
    public List<Student> getNumberOfAbsentLessons(int courseId) {
        List<Student> students = new ArrayList<>();

        String sql
                = "SELECT T1.id, T1.name, COUNT(T2.status) AS absentLessons "
                + "FROM "
                + "	(SELECT U.id, U.name "
                + "	FROM [User] AS U "
                + "	JOIN UserCourse AS UC ON U.id = UC.userId "
                + "	WHERE UC.courseId = ? AND U.userTypeId = 'S') "
                + "	AS T1 "
                + "LEFT JOIN "
                + "	(SELECT AR.userId, AR.status "
                + "	FROM AttendanceRecord AS AR "
                + "	JOIN CourseCalendar AS CC ON AR.courseCalendarId = CC.id "
                + "	WHERE CC.courseId = ? "
                + "	AND AR.status = 'Absent') "
                + "	AS T2 "
                + "ON T1.id = T2.userId "
                + "GROUP BY T1.id, T1.name "
                + "ORDER BY absentLessons DESC";

        try (Connection con = connection.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, courseId);
            pstmt.setInt(2, courseId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int absentCount = rs.getInt("absentLessons");                
                students.add(new Student(id, name, absentCount));
            }
            return students;
        } catch (SQLServerException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void createRecord(int userId, int courseCalenderId, CourseCal.StatusType status) {
        String sql = "INSERT INTO AttendanceRecord (userId, courseCalendarId, status) VALUES (?,?,?)";

        try (Connection con = connection.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, courseCalenderId);
            pstmt.setString(3, status.name());

            pstmt.executeUpdate();

        } catch (Exception e) {
        }

    }
    
    /*
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
}
