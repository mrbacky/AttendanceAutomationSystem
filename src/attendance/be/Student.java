package attendance.be;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author annem
 */
public class Student extends User {

    private final IntegerProperty absenceCount = new SimpleIntegerProperty();
    private final IntegerProperty absencePercentage = new SimpleIntegerProperty();

    public Student(int id, String name, int absentLessons) {
        super(id, name, UserType.STUDENT);
        this.absenceCount.set(absentLessons);
    }

    public int getAbsenceCount() {
        return absenceCount.get();
    }

    public void setAbsenceCount(int value) {
        absenceCount.set(value);
    }

    public IntegerProperty absenceCountProperty() {
        return absenceCount;
    }

    public int getAbsencePercentage() {
        return absencePercentage.get();
    }

    public void setAbsencePercentage(int value) {
        absencePercentage.set(value);
    }

    public IntegerProperty absencePercentageProperty() {
        return absencePercentage;
    }

}
