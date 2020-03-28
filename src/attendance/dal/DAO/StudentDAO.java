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
    public List<CourseCal> getAttendanceRecords(int userId, int courseId) {
        List<CourseCal> cc = new ArrayList<>();
        String sql
                = "SELECT AR.courseCalendarId, C.name, CC.startTime, CC.endTime, AR.status "
                + "FROM AttendanceRecord AR "
                + "JOIN CourseCalendar CC "
                + "	ON AR.courseCalendarId = CC.id "
                + "JOIN Course C "
                + "	ON CC.courseId = C.id "
                + "WHERE AR.userId = ? "
                + "	AND CC.courseId = ? "
                + "ORDER BY CC.startTime";
        try (Connection con = connection.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, courseId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("courseCalendarId");
                String courseName = rs.getString("name");
                LocalDateTime start = rs.getTimestamp("startTime").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("endTime").toLocalDateTime();
                String type = rs.getString("status");
                
                if (type.contains("PRESENT")) {
                    CourseCal c = new CourseCal(id, courseName, start, end, CourseCal.StatusType.PRESENT);
                    System.out.println("HERE " + c.getStatusType());
                    cc.add(c);
                    //cc.add(new CourseCal(id, courseName, start, end, CourseCal.StatusType.PRESENT));
                } else {
                    CourseCal c = new CourseCal(id, courseName, start, end, CourseCal.StatusType.ABSENT);
                     System.out.println("HERE " + c.getStatusType());
                    cc.add(c);
                    //cc.add(new CourseCal(id, courseName, start, end, CourseCal.StatusType.ABSENT));
                }
            }
            for (CourseCal c : cc) {
                System.out.println("id " + c.getId());
                System.out.println("cn " + c.getCourseName());
                System.out.println("st " + c.getStartTime());
                System.out.println("et " + c.getEndTime());
                System.out.println("st " + c.getStatusType());
            }
            return cc;
        } catch (SQLServerException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
}
