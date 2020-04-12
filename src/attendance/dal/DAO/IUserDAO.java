package attendance.dal.DAO;

import attendance.be.User;
import attendance.dal.DalException;

/**
 *
 * @author annem
 */
public interface IUserDAO {

    /**
     * Gets the user.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return
     * @throws DalException
     */
    User getUser(String username, String password) throws DalException;

}
