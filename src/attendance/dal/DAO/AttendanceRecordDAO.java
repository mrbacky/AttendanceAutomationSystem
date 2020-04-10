package attendance.dal.DAO;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.Student;
import attendance.be.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author annem
 */
public class AttendanceRecordDAO implements IAttendanceRecordDAO {

    private final DBConnectionProvider connection;

    public AttendanceRecordDAO() {
        connection = new DBConnectionProvider();
    }

    @Override
    public void createRecord(User student, Lesson lesson) {
        String sql = "INSERT INTO AttendanceRecord (userId, courseCalendarId, status, timeRecorded) VALUES (?,?,?,CURRENT_TIMESTAMP)";

        try (Connection con = connection.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, student.getId());
            pstmt.setInt(2, lesson.getId());
            pstmt.setString(3, lesson.getStatusType().toString());

            pstmt.executeUpdate();
        } catch (Exception e) {
        }
    }

    @Override
    public List<Lesson> getAttendanceRecordsForAllCourses(User student) {
        List<Lesson> cc = new ArrayList<>();
        String sql
                = "SELECT AR.courseCalendarId, C.name, CC.startTime, CC.endTime, AR.status "
                + "FROM AttendanceRecord AR "
                + "JOIN CourseCalendar CC "
                + "	ON AR.courseCalendarId = CC.id "
                + "JOIN Course C "
                + "	ON CC.courseId = C.id "
                + "WHERE AR.userId = ? "
                + "ORDER BY CC.startTime";
        try (Connection con = connection.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, student.getId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("courseCalendarId");
                String courseName = rs.getString("name");
                LocalDateTime start = rs.getTimestamp("startTime").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("endTime").toLocalDateTime();
                String type = rs.getString("status");

                if (type.contains("PRESENT")) {
                    cc.add(new Lesson(id, courseName, start, end, Lesson.StatusType.PRESENT));
                } else if (type.contains("ABSENT")) {
                    cc.add(new Lesson(id, courseName, start, end, Lesson.StatusType.ABSENT));
                }
            }
            return cc;
        } catch (SQLServerException ex) {
            Logger.getLogger(AttendanceRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Lesson> getAttendanceRecordsForACourse(User student, Course course) {
        List<Lesson> cc = new ArrayList<>();
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
            pstmt.setInt(1, student.getId());
            pstmt.setInt(2, course.getId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("courseCalendarId");
                String courseName = rs.getString("name");
                LocalDateTime start = rs.getTimestamp("startTime").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("endTime").toLocalDateTime();
                String type = rs.getString("status");

                if (type.contains("PRESENT")) {
                    cc.add(new Lesson(id, courseName, start, end, Lesson.StatusType.PRESENT));
                } else if (type.contains("ABSENT")) {
                    cc.add(new Lesson(id, courseName, start, end, Lesson.StatusType.ABSENT));
                }
            }
            return cc;
        } catch (SQLServerException ex) {
            Logger.getLogger(AttendanceRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Student> getNumberOfAbsentLessons(Course course) {
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
            pstmt.setInt(1, course.getId());
            pstmt.setInt(2, course.getId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int absentCount = rs.getInt("absentLessons");
                students.add(new Student(id, name, absentCount));
            }
            return students;
        } catch (SQLServerException ex) {
            Logger.getLogger(AttendanceRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int getAttendanceForLesson(Course course, LocalDateTime current) {
        String sql
                = "SELECT TOP 1 CC.startTime, AR.courseCalendarId, COUNT(AR.courseCalendarId) AS attendanceCount "
                + "	FROM AttendanceRecord AR "
                + "	JOIN CourseCalendar CC "
                + "		ON AR.courseCalendarId = CC.id "
                + "	WHERE CC.courseId = ? "
                + "		AND AR.status = 'PRESENT' "
                + "		AND	CC.startTime <= ? "
                + "		AND CC.endTime > ? "
                + "	GROUP BY CC.startTime, AR.courseCalendarId "
                + "	ORDER BY startTime DESC";

        try (Connection con = connection.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, course.getId());
            pstmt.setTimestamp(2, Timestamp.valueOf(current));
            pstmt.setDate(3, Date.valueOf(current.toLocalDate()));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(3);
                return count;
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(AttendanceRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceRecordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public LocalDateTime getTimeOfLastUpdate(Course course, LocalDate current) {
        String sql = "SELECT TOP 1 AR.timeRecorded "
                + "FROM AttendanceRecord AR "
                + "JOIN CourseCalendar CC "
                + "ON AR.courseCalendarId = CC.id "
                + "WHERE CC.courseId = ? "
                //+ "AND (CC.startTime >= ? AND CC.startTime < ?) "
                + "ORDER BY AR.timeRecorded DESC";

        try (Connection con = connection.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, course.getId());
//            pstmt.setDate(2, Date.valueOf(current));
//            pstmt.setDate(3, Date.valueOf(current.plusDays(1)));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                LocalDateTime timeRecorded = rs.getTimestamp("timeRecorded").toLocalDateTime();
                return timeRecorded;
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
