package attendance.bll;

import attendance.dal.DAO.CourseDAO;
import attendance.dal.DAO.ICourseDAO;
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
    private boolean isRunning = true;
    private List<DataObserver> observers;
    private String state;

    public ConcreteObservable() {
        cDAO = new CourseDAO();
        observers = new ArrayList<>();
        notifyObserver();
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
    public void notifyObserver() {
        Thread t = new Thread(() -> {
            while (isRunning) {
                if (cDAO.hasNewData()) {
                    setState(cDAO.getNewData());
                    for (DataObserver o : observers) {
                        o.update();
                    }
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

    public String getState() {
        return state;
    }

    private void setState(String newState) {
        state = newState;
    }

}
