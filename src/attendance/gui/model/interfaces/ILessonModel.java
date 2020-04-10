/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.gui.model.interfaces;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.Student;
import attendance.be.User;
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

    void loadAllLessons(User student, LocalDate current);

    ObservableList<Lesson> getObservableLessonList();
    

    void createRecord(User student, Lesson lessonToInsert);

    void loadAllRecords(User student);

    ObservableList<Lesson> getObservableRecordList();

    void filterByCourse(User student, Course course);

    int calculateAbsenceLabel(List<Lesson> list);

    int getAbsencePercentageLabel();

    void setAbsencePercentageLabel(int value);

    IntegerProperty absencePercentageLabelProperty();

    void startObserving(Student s, Course c);

    void stopObserving();
    
    ObservableList<XYChart.Data<String, Integer>> getObsWeekdayAbsenceCount();
    
    

}
