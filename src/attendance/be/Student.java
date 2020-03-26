package attendance.be;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author annem
 */
public class Student extends User {

    private final DoubleProperty absence = new SimpleDoubleProperty();
    
    public Student(int id, String name, double absence) {// absence to INT
        super(id, name, UserType.STUDENT);
        this.absence.set(absence);
    }

    public double getAbsence() {
        return absence.get();
    }

    public void setAbsence(double value) {
        absence.set(value);
    }

    public DoubleProperty absenceProperty() {
        return absence;
    }

}
