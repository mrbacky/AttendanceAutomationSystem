package attendance.gui.model.interfaces;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.Student;
import java.util.List;
import java.util.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

/**
 *
 * @author rado
 */
public interface IRecordModel {

    void loadAllRecords(int userId);

    ObservableList<Lesson> getRecordList();

    void startObserving(Student s, Course c);

    ObservableList<XYChart.Data<String, Integer>> getWeekdayAbsenceCount();

    void filterRecordsByCourse(int userId, int courseId);

    int calculateAbsenceLabel(List<Lesson> list);

    int getAbsencePercentageLabel();

    void setAbsencePercentageLabel(int value);

    IntegerProperty absencePercentageLabelProperty();

    void stopObserving();

}
