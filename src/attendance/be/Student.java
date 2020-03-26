package attendance.be;

/**
 *
 * @author annem
 */
public class Student extends User {

    private int absentCount;
    
    public Student(int id, String name) {
        super(id, name, UserType.Student);
    }

    public Student(int id, String name, int absentCount) {
        super(id, name, UserType.Student);
        this.absentCount = absentCount;
    }

}
