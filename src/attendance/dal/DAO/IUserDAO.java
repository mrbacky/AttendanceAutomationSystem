package attendance.dal.DAO;

import attendance.be.User;

/**
 *
 * @author annem
 */
public interface IUserDAO {

    User getUser(String username, String password);
}
