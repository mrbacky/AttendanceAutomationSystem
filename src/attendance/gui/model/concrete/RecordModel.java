package attendance.gui.model.concrete;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.Student;
import attendance.be.User;
import attendance.bll.IBLLFacade;
import attendance.bll.observable.ConcreteObservable2;
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

    private final ObservableList<XYChart.Data<String, Integer>> absencePerWeekday = FXCollections.observableArrayList();
    private ConcreteObservable2 bllComponent2;

    public RecordModel(IBLLFacade bllManager) {
        this.bllManager = bllManager;
    }

    @Override
    public void loadAllRecords(User student) {
        List<Lesson> allRecords = bllManager.getAttendanceRecordsForAllCourses(student);
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
    public ObservableList<XYChart.Data<String, Integer>> getWeekdayAbsenceCount() {
        return absencePerWeekday;
    }

    @Override
    public void startObserving(Student s, Course c) {
        ObserverEvent e = new ObserverEvent(c, s);
        bllComponent2 = new ConcreteObservable2(e);
        DataObserver observer = new DataObserver() {
            @Override
            public void update(ObserverEvent e) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        List<Lesson> records = bllComponent2.getRecordListState();
                        for (Lesson record : records) {
                            record.setDay();
                            record.setDate();
                        }
                        if (records != null) {
                            recordList.setAll(records);
                        }
                        List<XYChart.Data<String, Integer>> i = bllComponent2.getWeekdayAbsenceState();
                        if (i != null) {
                            absencePerWeekday.setAll(i);
                        }
                    }
                });
            }
        };
        bllComponent2.attach(observer);
    }

    @Override
    public void stopObserving() {
        bllComponent2.setIsRunning(false);
    }

    @Override
    public void filterRecordsByCourse(User student, Course course) {
        List<Lesson> temp = bllManager.getAttendanceRecordsForACourse(student, course);
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
        int absence = bllManager.countAbsentLessons(list);
        int h = list.size();
        return bllManager.calculatePercentage(absence, h);
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

}
