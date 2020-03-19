package attendance.be;

/**
 *
 * @author annem
 */
public class User {

    private int id;
    private String name;
    private UserType type;

    public User(int id, String name, UserType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UserType getType() {
        return type;
    }
        
    public enum UserType{
        Teacher, Student
    }

    
    
    
    
    public static String toString(String username) {
        return username;
    }

    private String username;
    private String password;
    private boolean isTeacher;

    public User(String username, String password, boolean isTeacher) {
        this.username = username;
        this.password = password;
        this.isTeacher = isTeacher;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(boolean isTeacher) {
        this.isTeacher = isTeacher;
    }
}
