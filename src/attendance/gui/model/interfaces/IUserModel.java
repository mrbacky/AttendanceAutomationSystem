package attendance.gui.model.interfaces;

import attendance.be.User;
import attendance.gui.model.ModelException;

/**
 *
 * @author rado
 */
public interface IUserModel {

    User getCurrentUser() throws ModelException;

    User login(String username, String password) throws ModelException;

    boolean usernameCheck(String username);

    boolean passwordCheck(String username);
}
