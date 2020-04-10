package attendance.gui.model.concrete;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.Student;
import attendance.bll.IBLLFacade;
import attendance.bll.observable.ConcreteObservable3;
import attendance.bll.observable.ConcreteObservable4;
import attendance.bll.observer.DataObserver;
import attendance.bll.util.AbsenceCounter;
import attendance.bll.util.AbsencePercentageCalculator;
import attendance.bll.util.ObserverEvent;
import attendance.gui.model.interfaces.IRecordModel;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

/**
 *
 * @author rado
 */
public class RecordModel implements IRecordModel {

    private final IBLLFacade bllManager;

    private final ObservableList<Lesson> recordList = FXCollections.observableArrayList();
    private final IntegerProperty absencePercentageLabel = new SimpleIntegerProperty();
    private final AbsenceCounter aCounter;
    private final AbsencePercentageCalculator aCalc;

    private final ObservableList<XYChart.Data<String, Integer>> absencePerWeekday = FXCollections.observableArrayList();
    private ConcreteObservable3 bllComponent3;
    private ConcreteObservable4 bllComponent4;

    public RecordModel(IBLLFacade bllManager) {
        this.bllManager = bllManager;
        aCounter = new AbsenceCounter();
        aCalc = new AbsencePercentageCalculator();
    }

    @Override
    public void loadAllRecords(int userId) {
        List<Lesson> allRecords = bllManager.getAttendanceRecordsForAllCourses(userId);
        for (Lesson record : allRecords) {
            record.setDay();
            record.setDate();
            record.setTimeFrame();
        }
        recordList.clear();
        recordList.addAll(allRecords);
        absencePercentageLabel.setValue(calculateAbsenceLabel(allRecords));

    }

    @Override
    public ObservableList<Lesson> getRecordList() {
        return recordList;
    }

    @Override
    public void startObserving(Student s, Course c) {
        ObserverEvent e = new ObserverEvent(c, s);
        bllComponent3 = new ConcreteObservable3(e);
        bllComponent4 = new ConcreteObservable4(e);
        DataObserver observer = (ObserverEvent e1) -> {
            Platform.runLater(() -> {
                List<Lesson> records = bllComponent3.getState();
                if (records != null) {
                    recordList.setAll(records);
                }
                List<XYChart.Data<String, Integer>> i = bllComponent4.getState();
                if (i != null) {
                    absencePerWeekday.setAll(i);
                }
            });
        };
        bllComponent3.attach(observer);
        bllComponent4.attach(observer);

    }

    @Override
    public ObservableList<XYChart.Data<String, Integer>> getWeekdayAbsenceCount() {
        return absencePerWeekday;
    }

    @Override
    public void filterRecordsByCourse(int userId, int courseId) {
        List<Lesson> temp = bllManager.getAttendanceRecordsForACourse(userId, courseId);
        for (Lesson record : temp) {
            record.setDay();
            record.setDate();
            record.setTimeFrame();
        }
        recordList.clear();
        recordList.addAll(temp);
        absencePercentageLabel.setValue(calculateAbsenceLabel(temp));
    }

    @Override
    public int calculateAbsenceLabel(List<Lesson> list) {
        int absence = aCounter.count(list);
        int h = list.size();
        return aCalc.calculatePercentage(absence, h);
    }

    @Override
    public int getAbsencePercentageLabel() {
        return absencePercentageLabel.get();
    }

    @Override
    public void setAbsencePercentageLabel(int value) {
        absencePercentageLabel.set(value);
    }

    @Override
    public IntegerProperty absencePercentageLabelProperty() {
        return absencePercentageLabel;
    }

    @Override
    public void stopObserving() {
        bllComponent3.setIsRunning(false);
        bllComponent4.setIsRunning(false);
    }

}
