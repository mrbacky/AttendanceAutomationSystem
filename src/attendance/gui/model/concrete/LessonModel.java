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

    private final IBLLFacade bllManager;
    private final ObservableList<Lesson> lessonList = FXCollections.observableArrayList();

    public LessonModel(IBLLFacade bllManager) {
        this.bllManager = bllManager;

    }

    /**
     *
     * @param userId
     * @param current
     */
    @Override
    public void loadLessonsForToday(int userId, LocalDate current) {// calculate absence here
        List<Lesson> allLessons = bllManager.getLessonsForToday(userId, current);
        lessonList.clear();
        lessonList.addAll(allLessons);
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<Lesson> getLessonsForToday() {
        return lessonList;
    }

    /**
     *
     * @param userId
     * @param lessonToInsert
     */
    @Override
    public void registerAttendance(int userId, Lesson lessonToInsert) {
        bllManager.createRecord(userId, lessonToInsert);
        int index = lessonList.indexOf(lessonToInsert);
        lessonList.set(index, lessonToInsert);
    }

}
