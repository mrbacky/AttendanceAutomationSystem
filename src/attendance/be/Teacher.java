package attendance.be;

/**
 *
 * @author annem
 */
public class Teacher extends User {

    public Teacher(int id, String name) {
        super(id, name, UserType.TEACHER);
    }

}
