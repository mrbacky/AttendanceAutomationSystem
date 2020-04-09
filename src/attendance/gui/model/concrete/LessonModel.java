package attendance.gui.model.concrete;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.Student;
import attendance.bll.BLLManager;
import attendance.bll.observable.ConcreteObservable3;
import attendance.bll.observable.ConcreteObservable4;
import attendance.bll.util.AbsenceCounter;
import attendance.bll.util.AbsencePercentageCalculator;
import attendance.gui.model.interfaces.ILessonModel;
import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import attendance.bll.IBLLFacade;
import attendance.bll.util.ObserverEvent;
import attendance.bll.observer.DataObserver;
import javafx.application.Platform;
import javafx.scene.chart.XYChart;

/**
 *
 * @author rado
 */
public class LessonModel implements ILessonModel {

    private final IBLLFacade bllFacade;
    private final ObservableList<Lesson> lessonList = FXCollections.observableArrayList();
    private final ObservableList<Lesson> recordList = FXCollections.observableArrayList();
    private final IntegerProperty absencePercentageLabel = new SimpleIntegerProperty();
    private final AbsenceCounter aCounter;
    private final AbsencePercentageCalculator aCalc;

    private final ObservableList<XYChart.Data<String, Integer>> absencePerWeekday = FXCollections.observableArrayList();
    private ConcreteObservable3 bllComponent3;
    private ConcreteObservable4 bllComponent4;

    public LessonModel(IBLLFacade bllManager) {
        this.bllFacade = bllManager;
        aCounter = new AbsenceCounter();
        aCalc = new AbsencePercentageCalculator();
    }

    /**
     *
     * @param userId
     * @param current
     */
    @Override
    public void loadAllLessons(int userId, LocalDate current) {// calculate absence here
        List<Lesson> allLessons = bllFacade.getLessonsForToday(userId, current);
        lessonList.clear();
        lessonList.addAll(allLessons);
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<Lesson> getObservableLessonList() {
        return lessonList;
    }

    @Override
    public ObservableList<Lesson> getObservableRecordList() {
        return recordList;
    }

    @Override
    public ObservableList<XYChart.Data<String, Integer>> getObsWeekdayAbsenceCount() {
        return absencePerWeekday;
    }

    /**
     *
     * @param userId
     * @param lessonToInsert
     */
    @Override
    public void createRecord(int userId, Lesson lessonToInsert) {
        bllFacade.createRecord(userId, lessonToInsert);
        int index = lessonList.indexOf(lessonToInsert);
        lessonList.set(index, lessonToInsert);
    }

    /**
     *
     * @param userId
     */
    @Override
    public void loadAllRecords(int userId) {
        List<Lesson> allRecords = bllFacade.getAttendanceRecordsForAllCourses(userId);
        for (Lesson record : allRecords) {
            record.setDay();
            record.setDate();
            record.setTimeFrame();
        }
        recordList.clear();
        recordList.addAll(allRecords);
        absencePercentageLabel.setValue(calculateAbsenceLabel(allRecords));
    }

    /**
     *
     * @param userId
     * @param courseId
     */
    @Override
    public void filterByCourse(int userId, int courseId) {
        List<Lesson> temp = bllFacade.getAttendanceRecordsForACourse(userId, courseId);
        for (Lesson record : temp) {
            record.setDay();
            record.setDate();
            record.setTimeFrame();
        }
        recordList.clear();
        recordList.addAll(temp);
        absencePercentageLabel.setValue(calculateAbsenceLabel(temp));
    }

    /**
     *
     * @param list
     * @return
     */
    @Override
    public int calculateAbsenceLabel(List<Lesson> list) {
        int absence = aCounter.count(list);
        int h = list.size();
        return aCalc.calculatePercentage(absence, h);
    }

    /**
     *
     * @return
     */
    @Override
    public int getAbsencePercentageLabel() {
        return absencePercentageLabel.get();
    }

    /**
     *
     * @param value
     */
    @Override
    public void setAbsencePercentageLabel(int value) {
        absencePercentageLabel.set(value);
    }

    /**
     *
     * @return
     */
    @Override
    public IntegerProperty absencePercentageLabelProperty() {
        return absencePercentageLabel;
    }

    @Override
    public void startObserving(Student s, Course c) {
        ObserverEvent e = new ObserverEvent(c, s);
        bllComponent3 = new ConcreteObservable3(e);
        bllComponent4 = new ConcreteObservable4(e);
        DataObserver observer = new DataObserver() {
            @Override
            public void update(ObserverEvent e) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        List<Lesson> l = bllComponent3.getState();
                        if (l != null) {
                            recordList.setAll(l);
                        }
                        List<XYChart.Data<String, Integer>> i = bllComponent4.getState();
                        if (i != null) {
                            absencePerWeekday.setAll(i);
                        }
                    }
                });
            }
        };
        bllComponent3.attach(observer);
        bllComponent4.attach(observer);
    }

    /**
     *
     */
    @Override
    public void stopObserving() {
        bllComponent3.setIsRunning(false);
        bllComponent4.setIsRunning(false);
    }

}
