package attendance.bll;

import attendance.be.Student;
import attendance.bll.util.AbsencePercentageCalculator;
import attendance.dal.DAO.CourseDAO;
import attendance.dal.DAO.ICourseDAO;
import attendance.dal.DAO.IStudentDAO;
import attendance.dal.DAO.StudentDAO;
import attendance.dal.DalFacade;
import attendance.dal.DalManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author annem
 */
public class ConcreteObservable2 implements DataObservable {

    private final DalFacade dalFacade;
    private final AbsencePercentageCalculator calculator;
    private boolean isRunning = true;
    private final List<DataObserver> observers;
    private LocalDateTime lastReceivedUpdate;
    private List<Student> state;

    public ConcreteObservable2(ObserverEvent e) {
        dalFacade = new DalManager();
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
                if (dalFacade.hasUpdate(e.getCourse().getId(), lastReceivedUpdate)) {
                    int conductedLessons = dalFacade.getNumberOfConductedLessons(e.getCourse(), LocalDateTime.now());

                    List<Student> students = dalFacade.getNumberOfAbsentLessons(e.getCourse());

                    for (Student s : students) {
                        s.setAbsencePercentage(calculator.calculatePercentage(s.getAbsenceCount(), conductedLessons));
                    }
                    setState(students);
                    for (DataObserver o : observers) {
                        o.update(e);
                    }
                    lastReceivedUpdate = LocalDateTime.now();
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ConcreteObservable2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public List<Student> getState() {
        return state;
    }

    public void setState(List<Student> state) {
        this.state = state;
    }

}