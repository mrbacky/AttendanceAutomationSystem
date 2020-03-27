package attendance.dal.DAO;

import attendance.be.Course;
import attendance.be.User;
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
public class UserDAO implements IUserDAO {

    private final DBConnectionProvider connection;

    public UserDAO() {
        connection = new DBConnectionProvider();
    }

    @Override
    public User getUser(String username, String password) {
        String sql = "SELECT * FROM [User] WHERE username = ? AND password = ?";

        try (Connection con = connection.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String type = rs.getString("userTypeId");

            if (type.equals("T")) {
                return new User(id, name, User.UserType.TEACHER);
            } else {
                return new User(id, name, User.UserType.STUDENT);
            }

        } catch (SQLServerException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
                System.out.println(courseId);
                System.out.println(courseName);
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
