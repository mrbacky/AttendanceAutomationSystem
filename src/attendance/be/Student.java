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

    private final IntegerProperty absence = new SimpleIntegerProperty();
    private IntegerProperty lessonCount = new SimpleIntegerProperty();
    public Student(int id, String name, int absence, int lessonCount) {
        super(id, name, UserType.STUDENT);
        this.absence.set(absence);
        this.lessonCount.set(lessonCount);
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
