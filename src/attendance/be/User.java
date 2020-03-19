package attendance.be;

/**
 *
 * @author annem
 */
public class User {
    
    
    
    private String realName;
    private String username;
    private String password;
    private char isTeacher;

    public User(String realName, String username, String password, char isTeacher) {
        this.realName = realName;
        this.username = username;
        this.password = password;
        this.isTeacher = isTeacher;
    }

    
    
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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

    public char getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(char isTeacher) {
        this.isTeacher = isTeacher;
    }

    
}
