package attendance.dal.DAO;

import attendance.be.Course;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author annem
 */
public class CourseDAO implements ICourseDAO {

    private final DBConnectionProvider cp;

    /**
     * Constructor, which creates the connection with the database.
     */
    public CourseDAO() {
        cp = new DBConnectionProvider();
    }

    public ArrayList<Course> getCourse() throws SQLServerException {
        ArrayList<Course> courses = new ArrayList();
        String sql = "SELECT * FROM CourseCalender,Course JOIN Course ON CourseCalender.id = Course.id";
        try (Connection con = cp.getConnection()) {
            PreparedStatement ptst = con.prepareStatement(sql);
            ResultSet rs = ptst.executeQuery();
            while (rs.next()) {
                Course c = new Course(null, null, null);
                c.setName(rs.getString("name"));
                c.setStartDuration(rs.getTime("startTime").toString());
                c.setEndDuration(rs.getTime("endTime").toString());
                courses.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return courses;
    }

}
