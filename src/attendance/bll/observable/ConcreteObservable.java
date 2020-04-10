package attendance.bll.observable;

import attendance.be.Course;
import attendance.be.Student;
import attendance.bll.observer.DataObserver;
import attendance.bll.util.AbsencePercentageCalculator;
import attendance.bll.util.ObserverEvent;
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
public final class ConcreteObservable implements DataObservable {

    private final IDALFacade dalFacade;
    private final AbsencePercentageCalculator calculator;
    private boolean isRunning = true;
    private final List<DataObserver> observers;
    private LocalDateTime lastReceivedUpdate;
    private int presentCountStatus;
    private List<Student> studentListState;
    
    public ConcreteObservable(ObserverEvent e) {
        dalFacade = new DALManager();
        calculator = new AbsencePercentageCalculator();
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
                Course course = e.getCourse();
                if (dalFacade.hasUpdate(course, lastReceivedUpdate)) {
                    setPresentCountState(dalFacade.getAttendanceForLesson(course, LocalDateTime.now()));
                    
                    int conductedLessons = dalFacade.getNumberOfConductedLessons(course, LocalDateTime.now());
                    List<Student> students = dalFacade.getNumberOfAbsentLessons(course);
                    for (Student s : students) {
                        s.setAbsencePercentage(calculator.calculatePercentage(s.getAbsenceCount(), conductedLessons));
                    }
                    setStudentListState(students);
                    
                    for (DataObserver o : observers) {
                        o.update(e);
                    }
                    lastReceivedUpdate = LocalDateTime.now();
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ConcreteObservable.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public int getPresentCountState() {
        return presentCountStatus;
    }

    private void setPresentCountState(int newState) {
        this.presentCountStatus = newState;
    }

    public List<Student> getStudentListState() {
        return studentListState;
    }

    public void setStudentListState(List<Student> newState) {
        this.studentListState = newState;
    }
}