package attendance.gui.model.concrete;

import attendance.gui.model.ModelException;
import attendance.be.User;
import attendance.bll.util.LogicException;
import attendance.gui.model.interfaces.IUserModel;
import attendance.bll.IBLLFacade;

/**
 *
 * @author Martin
 */
public class UserModel implements IUserModel {

    private User currentUser;

    private IBLLFacade bllFacade;

    public UserModel(IBLLFacade bllFacade) {
        this.bllFacade = bllFacade;
    }

    @Override
    public User getCurrentUser() throws ModelException {    // not used
        //check parameter example
        if (currentUser.getId() < 1) {
            throw new ModelException("There is no such user.");
        }
        return currentUser;
    }

    @Override
    public User login(String username, String password) throws ModelException {
        //check username, password
        if (!usernameCheck(username)) {
            throw new ModelException("The username does not exist.");
        }
        if (!passwordCheck(password)) {
            throw new ModelException("The password is invalid.");
        } else {
            try {
                this.currentUser = bllFacade.getUser(username, password);    // this                                       d ddd dd
            } catch (LogicException ex) {
                throw new ModelException(ex.getMessage());
            }
        }
        return currentUser;
    }

    @Override
    public boolean usernameCheck(String username) {
        if (username.length() != 8) {
            return false;
        }
        return username.matches(".*([a-zA-Z].*[0-9]|[0-9].*[a-zA-Z]).*");
    }

    @Override
    public boolean passwordCheck(String username) {
        if (username.length() < 8 || username.length() > 20) {
            return false;
        }
        return username.matches(".*([a-zA-Z].*[0-9]|[0-9].*[a-zA-Z]).*");
    }

}
