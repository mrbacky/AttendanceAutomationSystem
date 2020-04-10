package attendance.bll.observable;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.Student;
import attendance.be.User;
import attendance.bll.util.ObserverEvent;
import attendance.bll.observer.DataObserver;
import attendance.dal.IDALFacade;
import attendance.dal.DALManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author annem
 */
public class ConcreteObservable3 implements DataObservable {

    private final IDALFacade dalfacade;
    private boolean isRunning = true;
    private List<DataObserver> observers;
    private LocalDateTime lastReceivedUpdate;
    private List<Lesson> recordListState;

    public ConcreteObservable3(ObserverEvent e) {
        dalfacade = new DALManager();
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
                if (dalfacade.hasUpdate(e.getCourse(), lastReceivedUpdate)) {
                    User student = e.getStudent();
                    Course course = e.getCourse();
                    List<Lesson> lessons = dalfacade.getAttendanceRecordsForACourse(student, course);
                    setRecordListState(lessons);
                    for (DataObserver o : observers) {
                        o.update(e);
                    }
                    lastReceivedUpdate = LocalDateTime.now();
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ConcreteObservable3.class.getName()).log(Level.SEVERE, null, ex);
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

    public List<Lesson> getRecordListState() {
        return recordListState;
    }

    public void setRecordListState(List<Lesson> state) {
        this.recordListState = state;
    }

}