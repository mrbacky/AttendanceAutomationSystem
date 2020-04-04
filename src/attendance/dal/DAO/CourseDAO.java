package attendance.dal.DAO;

import attendance.be.Course;
import attendance.be.Lesson;
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
public class CourseDAO implements ICourseDAO {

    private final DBConnectionProvider connection;

    /**
     * Constructor, which creates the connection with the database.
     */
    public CourseDAO() {
        connection = new DBConnectionProvider();
    }

    @Override
    public List<Course> getCourses(int userId) {
        List<Course> courses = new ArrayList<>();

        String sql = "SELECT C.id, C.name "
                + "FROM Course C "
                + "JOIN UserCourse UC ON C.id = UC.courseId "
                + "WHERE userId = ?";

        try (Connection con = connection.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int courseId = rs.getInt("id");
                String courseName = rs.getString("name");
                courses.add(new Course(courseId, courseName));
            }
            return courses;
        } catch (SQLServerException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Lesson> getLessonsForToday(int userId, LocalDate current) {
        List<Lesson> courses = new ArrayList<>();

        String sql = "SELECT CC.id, C.name, CC.startTime, CC.endTime "
                + "FROM CourseCalendar CC "
                + "JOIN Course C "
                + "ON CC.courseId = C.id "
                + "WHERE ";

        List<Course> cs = getCourses(userId);

        String sqlFinal = preparedStatement(sql, cs);

        try (Connection con = connection.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sqlFinal);
            int i = 0;
            for (Course c : cs) {
                pstmt.setInt(i + 1, c.getId());
                i++;
            }
            pstmt.setDate(i + 1, Date.valueOf(current));
            pstmt.setDate(i + 2, Date.valueOf(current.plusDays(1)));

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String courseName = rs.getString("name");
                LocalDateTime start = rs.getTimestamp("startTime").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("endTime").toLocalDateTime();
                String status = checkStatus(userId, id);
                if (status.contains("PRESENT")) {
                    courses.add(new Lesson(id, courseName, start, end, Lesson.StatusType.PRESENT));
                } else if (status.contains("ABSENT")) {
                    courses.add(new Lesson(id, courseName, start, end, Lesson.StatusType.ABSENT));
                } else {
                    courses.add(new Lesson(id, courseName, start, end, Lesson.StatusType.UNREGISTERED));
                }
            }
            for (Lesson c : courses) {
            }
            return courses;
        } catch (SQLServerException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * The conditions of the SQL PreparedStatement for getLessonsForToday().
     *
     * @param sql The SQL PreparedStatement.
     * @param courses
     * @return
     */
    private String preparedStatement(String sql, List<Course> courses) {
        boolean firstItem = true;
        for (Course c : courses) {
            if (firstItem) {
                sql += "(CC.courseId = ? ";
                firstItem = false;
            } else {
                sql += " OR CC.courseId = ?";
            }
        }
        sql += ") AND (CC.startTime >= ? AND CC.startTime < ?)";
        return sql;
    }

    private String checkStatus(int userId, int calId) {
        String sql = "SELECT status "
                + "FROM AttendanceRecord "
                + "WHERE userId = ? AND courseCalendarId = ?;";
        try (Connection con = connection.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, calId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String status = rs.getString(1);
                return status;
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String s = "UNREGISTERED";
        return s;
    }

    @Override
    public int getNumberOfConductedLessons(Course course, LocalDateTime current) {
        String sql = "SELECT COUNT(id) FROM CourseCalendar WHERE courseId = ? AND endTime <= ?";

        try (Connection con = connection.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, course.getId());
            pstmt.setTimestamp(2, Timestamp.valueOf(current));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count;
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
