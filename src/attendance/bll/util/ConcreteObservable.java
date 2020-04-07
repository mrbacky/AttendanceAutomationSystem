package attendance.bll.util;

import attendance.be.Course;
import attendance.dal.DALManager;
import attendance.dal.DAO.CourseDAO;
import attendance.dal.DAO.ICourseDAO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import attendance.dal.IDALFacade;

/**
 *
 * @author annem
 */
public final class ConcreteObservable implements DataObservable {

    private final ICourseDAO cDAO;
    private final IDALFacade dalfacade;
    private boolean isRunning = true;
    private final List<DataObserver> observers;    
    private LocalDateTime lastReceivedUpdate;
    private int state;

    public ConcreteObservable(Course c) {
        cDAO = new CourseDAO();
        dalfacade = new DALManager();
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
        Thread t = new Thread(() -> {
            while (isRunning) {
            if (dalfacade.hasUpdate(c.getId(),lastReceivedUpdate)) {
                    setState(cDAO.getAttendanceForLesson(c.getId(), LocalDateTime.now()));                    
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
