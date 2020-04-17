package attendance.dal.DAO.concrete;

import attendance.be.Course;
import attendance.be.User;
import attendance.dal.DBConnectionProvider;
import attendance.dal.DAO.interfaces.ICourseDAO;
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
public class CourseDAO implements ICourseDAO {

    private final DBConnectionProvider connection;

    /**
     * Constructor, which creates the connection with the database.
     */
    public CourseDAO() {
        connection = new DBConnectionProvider();
    }

    @Override
    public List<Course> getCourses(User user) {
        List<Course> courses = new ArrayList<>();

        String sql = "SELECT C.id, C.name "
                + "FROM Course C "
                + "JOIN UserCourse UC ON C.id = UC.courseId "
                + "WHERE userId = ?";

        try (Connection con = connection.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, user.getId());

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

}
