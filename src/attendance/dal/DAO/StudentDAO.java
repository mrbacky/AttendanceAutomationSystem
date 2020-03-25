/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.dal.DAO;

import attendance.be.Course;
import attendance.be.Student;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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

    /*As a teacher, I should be able to see summarized attendance for a specific course,
    where most absent students are at the top.
    
    1. total number of lessons taken place until current date
    parameters: courseId, date, time?
    SELECT COUNT(id) FROM CourseCalendar WHERE courseId = ? AND date <= ? AND time???
    SELECT COUNT(id) FROM CourseCalendar WHERE courseId = 2 AND endTime <= '2020-03-09 14:29:00'

    2. number of lessons each student in the course has attended
    courseId -> find userId
     */
    public int getNumberOfConductedLessons(int courseId, LocalDateTime endTime) {
        String sql = "SELECT COUNT(id) FROM CourseCalendar WHERE courseId = ? AND endTime <= ?";

        try (Connection con = connection.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, courseId);
            //not sure about this
            pstmt.setTimestamp(2, Timestamp.valueOf(endTime));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                System.out.println("count is" + count);
                return count;
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    //public List<Student> getNumberOfAbsentLessons(Course course) {
    public List<Student> getNumberOfAbsentLessons(int courseId) {
        List<Student> lst = new ArrayList<>();

        //add SQL statement
        String sql = "";

        try (Connection con = connection.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, courseId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                //check names below
                int id = rs.getInt("userId");
                String name = rs.getString("name");
                lst.add(new Student(id, name));
            }
            return lst;
        } catch (SQLServerException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
