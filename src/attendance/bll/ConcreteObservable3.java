package attendance.bll;

import attendance.be.Lesson;
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
public class ConcreteObservable3 implements DataObservable {

    private final ICourseDAO cDAO;
    private final IStudentDAO sDAO;
    private final DalFacade dalfacade;
    private boolean isRunning = true;
    private List<DataObserver> observers;
    private LocalDateTime lastReceivedUpdate;
    private List<Lesson> state;

    public ConcreteObservable3(ObserverEvent e) {
        cDAO = new CourseDAO();
        sDAO = new StudentDAO();
        dalfacade = new DalManager();
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
                System.out.println("Concrete3");
                if (dalfacade.hasUpdate(e.getCourse().getId(), lastReceivedUpdate)) {
                    System.out.println("notify id: " + e.getStudent().getId());
                    int userId = e.getStudent().getId();
                    int courseId = e.getCourse().getId();
                    List<Lesson> lessons = dalfacade.getAttendanceRecordsForACourse(userId, courseId);
                    setState(lessons);
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

    public List<Lesson> getState() {
        return state;
    }

    public void setState(List<Lesson> state) {
        this.state = state;
    }

}
