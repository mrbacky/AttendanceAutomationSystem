package attendance.dal.DAO;

import attendance.be.Student;
import attendance.be.Teacher;
import attendance.be.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                return new User(id, name, User.UserType.Teacher);
            } else {
                return new User(id, name, User.UserType.Student);
            }

        } catch (SQLServerException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
