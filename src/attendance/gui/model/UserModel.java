package attendance.gui.model;

import attendance.be.User;
import attendance.bll.LogicException;
import attendance.bll.LogicFacade;
import attendance.bll.LogicManager;

/**
 *
 * @author Martin
 */
public class UserModel {

    private static UserModel model;
    private User currentUser;

    private final LogicFacade logicManager;

    /**
     * Create instance of Singleton.
     *
     * @return
     */
    public static UserModel getInstance() {
        if (model == null) {
            model = new UserModel();
        }
        return model;
    }

    private UserModel() {
        logicManager = new LogicManager();
    }

    public User getCurrentUser() throws ModelException {
        //check parameter example
        if (currentUser.getId() < 1) {
            throw new ModelException("There is no such user.");
        }
        return currentUser;
    }

    public User login(String username, String password) throws ModelException {
        //check username, password
        if (!usernameCheck(username)) {
            throw new ModelException("The username does not exist.");
        }
        if (!passwordCheck(password)) {
            throw new ModelException("The password is invalid.");
        } else {
            try {
                currentUser = logicManager.getUser(username, password);
            } catch (LogicException ex) {
                throw new ModelException(ex.getMessage());
            }
        }

        return currentUser;
    }

    private boolean usernameCheck(String username) {
        if (username.length() != 8) {
            return false;
        }
        return username.matches(".*([a-zA-Z].*[0-9]|[0-9].*[a-zA-Z]).*");
    }

    private boolean passwordCheck(String username) {
        if (username.length() < 8 || username.length() > 20) {
            return false;
        }
        return username.matches(".*([a-zA-Z].*[0-9]|[0-9].*[a-zA-Z]).*");
    }
}
