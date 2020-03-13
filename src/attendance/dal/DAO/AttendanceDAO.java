package attendance.dal.DAO;

/**
 *
 * @author annem
 */
public class AttendanceDAO implements IAttendanceDAO {

    private final DBConnectionProvider connection;

    /**
     * Constructor, which creates the connection with the database.
     */
    public AttendanceDAO() {
        connection = new DBConnectionProvider();
    }

}
