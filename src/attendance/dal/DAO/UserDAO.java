package attendance.dal.DAO;

/**
 *
 * @author annem
 */
public class UserDAO implements IUserDAO {
    private final DBConnectionProvider connection;

    public UserDAO() {
        connection = new DBConnectionProvider();
    }

}
