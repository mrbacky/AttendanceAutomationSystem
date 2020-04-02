package attendance.be;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author annem
 */
public class Student extends User {

    private final DoubleProperty absence = new SimpleDoubleProperty();
    private IntegerProperty lessonCount = new SimpleIntegerProperty();
    
    public Student(int id, String name, double absence, int lessonCount) {// absence to INT
        super(id, name, UserType.STUDENT);
        this.absence.set(absence);
        this.lessonCount.set(lessonCount);
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

    public IntegerProperty getLessonCount() {
        return lessonCount;
    }

    public void setLessonCount(IntegerProperty lessonCount) {
        this.lessonCount = lessonCount;
    }

    public IntegerProperty lessonCountProperty() {
         return   lessonCount;
    }
    
    
    
}
