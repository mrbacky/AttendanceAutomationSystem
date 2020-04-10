package attendance.gui.model.interfaces;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.Student;
import attendance.be.User;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

/**
 *
 * @author rado
 */
public interface IRecordModel {

    void loadAllRecords(User student);

    ObservableList<Lesson> getRecordList();

    void startObserving(Student s, Course c);

    ObservableList<XYChart.Data<String, Integer>> getWeekdayAbsenceCount();

    void filterRecordsByCourse(User student, Course course);

    int calculateAbsenceLabel(List<Lesson> list);

    int getAbsencePercentageLabel();

    void setAbsencePercentageLabel(int value);

    IntegerProperty absencePercentageLabelProperty();

    void stopObserving();

}
