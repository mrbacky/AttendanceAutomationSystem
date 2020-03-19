package attendance.be;

/**
 *
 * @author annem
 */
public class Student extends User {

    public Student(int id, String name) {
        super(id, name, UserType.Student);
    }


}
