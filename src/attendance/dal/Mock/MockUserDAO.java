package attendance.dal.Mock;

import attendance.be.User;
import java.util.ArrayList;
import java.util.List;

public class MockUserDAO {

    List<User> tempUsers = new ArrayList<>();
    private User currentUser;

    public MockUserDAO() {
        createTempUsers();
    }

    public void createTempUsers() {
        tempUsers.add(new User("Jeppe Moritz", "jeppe1", "1111", 'T'));
        tempUsers.add(new User("Peter Stegger", "peter2", "2222", 'T'));
        tempUsers.add(new User("Radoslav Backovsky", "rado3", "3333", 'S'));
        tempUsers.add(new User("Louise Lauenborg", "chili4", "4444", 'S'));
        tempUsers.add(new User("Anne Luong", "anne5", "5555", 'S'));
        tempUsers.add(new User("Martin Emil Wøbbe", "martin6", "6666", 'S'));

    }

    public User auth(String insertedUsername, String insertedPassword) {

        for (User tempUser : tempUsers) {
            if (tempUser.getUsername().equals(insertedUsername) && tempUser.getPassword().equals(insertedPassword)) {
                currentUser = tempUser;
                return tempUser;
            }
        }
    */
    }

        return null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

}
