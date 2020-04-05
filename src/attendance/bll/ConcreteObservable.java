package attendance.bll;

import attendance.be.Course;
import attendance.dal.DAO.CourseDAO;
import attendance.dal.DAO.ICourseDAO;
import attendance.dal.DalFacade;
import attendance.dal.DalManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author annem
 */
public class ConcreteObservable implements DataObservable {

    private ICourseDAO cDAO;
    private DalFacade dalfacade;
    private boolean isRunning = true;
    private List<DataObserver> observers;
    private int state;
    private LocalDateTime lastReceivedUpdate;

    public ConcreteObservable(Course c) {
        cDAO = new CourseDAO();
        dalfacade = new DalManager();
        observers = new ArrayList<>();
        lastReceivedUpdate = LocalDateTime.MIN;
        notifyObserver(c);
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
    public void notifyObserver(Course c) {
        System.out.println("????");
        Thread t = new Thread(() -> {
            while (isRunning) {
                System.out.println(dalfacade.hasUpdate(c.getId(),lastReceivedUpdate));
            if (dalfacade.hasUpdate(c.getId(),lastReceivedUpdate)) {
                    setState(cDAO.getAttendanceForLesson(c.getId(), LocalDateTime.now()));
                    System.out.println("state: " + getState());
                    for (DataObserver o : observers) {
                        o.update(c);
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

    public int getState() {
        return state;
    }

    private void setState(int newState) {
        state = newState;
    }

}
