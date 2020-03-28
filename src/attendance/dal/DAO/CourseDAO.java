package attendance.dal.DAO;

import attendance.be.Course;
import attendance.be.CourseCal;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author annem
 */
public class CourseDAO implements ICourseDAO {

    private final DBConnectionProvider cp;
    private final UserDAO userDAO;

    /**
     * Constructor, which creates the connection with the database.
     */
    public CourseDAO() {
        cp = new DBConnectionProvider();
        userDAO = new UserDAO();

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
    //public List<CourseCal> getCourseCal(int courseId, int userId, LocalDateTime current) throws SQLServerException {
    public List<CourseCal> getCourseCal(int userId, LocalDate current) throws SQLServerException {
        List<CourseCal> courses = new ArrayList<>();

        String sql = "SELECT CC.id, C.name, CC.startTime, CC.endTime "
                + "FROM CourseCalendar CC "
                + "JOIN Course C "
                + "ON CC.courseId = C.id "
                + "WHERE ";
        List<Course> cs = userDAO.getCourses(userId);

        String sqlFinal = preparedStatement(sql, cs);

        try (Connection con = cp.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sqlFinal);

            int i = 0;
            for (Course c : cs) {
                pstmt.setInt(i + 1, c.getId());
                i++;
            }

            pstmt.setDate(i + 1, Date.valueOf(current));
            pstmt.setDate(i + 2, Date.valueOf(current.plusDays(1)));
            //pstmt.setTimestamp(i + 2, Timestamp.valueOf(current.atStartOfDay()));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String courseName = rs.getString("name");
                LocalDateTime start = rs.getTimestamp("startTime").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("endTime").toLocalDateTime();
                System.out.println("id is " + id);
                System.out.println("course is " + courseName);
                System.out.println("start is " + start);
                System.out.println("end is " + end);
                courses.add(new CourseCal(id, courseName, start, end, CourseCal.StatusType.UNREGISTERED));
            }
            System.out.println("number of rows " + courses.size());
            return courses;
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

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

        //sql += ")";
        //sql += ") AND CC.startTime = ?"
        //+ "BETWEEN '?' AND '? 23:59:59'";  
        sql += ") AND (CC.startTime >= ? AND CC.startTime < ?)";

        System.out.println(sql);
        return sql;
    }
}
