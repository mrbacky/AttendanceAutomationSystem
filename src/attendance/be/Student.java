package attendance.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author annem
 */
public class Student extends User {

    private final IntegerProperty absence = new SimpleIntegerProperty();
    private int absenceCount;

    public Student(int id, String name, int absence) {
        super(id, name, UserType.STUDENT);
        //this.absence.set(absence);
        this.absenceCount = absence;
    }

    public int getAbsenceCount() {
        return absenceCount;
    }

    public void setAbsenceCount(int absenceCount) {
        this.absenceCount = absenceCount;
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
