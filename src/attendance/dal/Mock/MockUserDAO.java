package attendance.dal.Mock;

import attendance.be.User;

public class MockUserDAO {

    public User auth(String insertedUsername, String password) {
        User staticUs = new User("chili", "chili", false);

        User staticTeach = new User("Jep", "Jeppe", true);

        if (insertedUsername.equals("chili") && password.equals("chili")) {
            return staticUs;
        } else if (insertedUsername.equals("Jep") && password.equals("Jeppe")) {
            return staticTeach;
        } else {
            return null;
        }

    }
}
