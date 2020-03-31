package attendance.dal.DAO;

import attendance.be.User;
import attendance.dal.DalException;

/**
 *
 * @author annem
 */
public interface IUserDAO {

    User getUser(String username, String password) throws DalException;

}
