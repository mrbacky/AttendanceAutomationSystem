package attendance.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author annem
 */
public class Student extends User {

    private final IntegerProperty absence = new SimpleIntegerProperty();

    public Student(int id, String name, int absence) {
        super(id, name, UserType.STUDENT);
        this.absence.set(absence);
    }

    public int getAbsence() {
        return absence.get();
    }

    public void setAbsence(int value) {
        absence.set(value);
    }

    public IntegerProperty absenceProperty() {
        return absence;
    }

}
