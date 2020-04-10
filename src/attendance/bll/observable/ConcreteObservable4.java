package attendance.bll.observable;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.Student;
import attendance.be.User;
import attendance.bll.util.ObserverEvent;
import attendance.bll.util.DailyAbsenceCounter;
import attendance.bll.observer.DataObserver;
import attendance.dal.IDALFacade;
import attendance.dal.DALManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.XYChart;

/**
 *
 * @author annem
 */
public class ConcreteObservable4 implements DataObservable {

    private final IDALFacade dalFacade;
    private final DailyAbsenceCounter dailyAbsenceCounter;
    private boolean isRunning = true;
    private List<DataObserver> observers;
    private LocalDateTime lastReceivedUpdate;
    private List<XYChart.Data<String, Integer>> state;

    public ConcreteObservable4(ObserverEvent e) {
        dalFacade = new DALManager();
        dailyAbsenceCounter = new DailyAbsenceCounter();
        observers = new ArrayList<>();
        lastReceivedUpdate = LocalDateTime.MIN;
        notifyObserver(e);
    }

    @Override
    public void attach(DataObserver o) {
        this.observers.add(o);
    }

    @Override
    public void detach(DataObserver o) {
        this.observers.remove(o);
    }

    @Override
    public void notifyObserver(ObserverEvent e) {
        Thread t = new Thread(() -> {
            while (isRunning) {
                if (dalFacade.hasUpdate(e.getCourse(), lastReceivedUpdate)) {
                    User student = e.getStudent();
                    Course course = e.getCourse();
                    List<Lesson> lessons = dalFacade.getAttendanceRecordsForACourse(student, course);
                    List<XYChart.Data<String, Integer>> weekdayAbsenceForCourse = dailyAbsenceCounter.getWeekdayAbsence(lessons);
                    setState(weekdayAbsenceForCourse);
                    for (DataObserver o : observers) {
                        o.update(e);
                    }
                    lastReceivedUpdate = LocalDateTime.now();
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ConcreteObservable4.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    public boolean isIsRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public List<XYChart.Data<String, Integer>> getState() {
        return state;
    }

    public void setState(List<XYChart.Data<String, Integer>> state) {
        this.state = state;
    }

}