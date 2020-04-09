/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.model.interfaces;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.Student;
import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

/**
 *
 * @author rado
 */
public interface ILessonModel {

    void loadAllLessons(int userId, LocalDate current);

    ObservableList<Lesson> getObservableLessonList();
    

    void createRecord(int userId, Lesson lessonToInsert);

    void loadAllRecords(int userId);

    ObservableList<Lesson> getObservableRecordList();

    void filterByCourse(int userId, int courseId);

    int calculateAbsenceLabel(List<Lesson> list);

    int getAbsencePercentageLabel();

    void setAbsencePercentageLabel(int value);

    IntegerProperty absencePercentageLabelProperty();

    void startObserving(Student s, Course c);

    void stopObserving();
    
    ObservableList<XYChart.Data<String, Integer>> getObsWeekdayAbsenceCount();
    
    

}
