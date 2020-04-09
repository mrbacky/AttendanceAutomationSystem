package attendance.bll.observable;

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
public final class ConcreteObservable implements DataObservable {

    private final IDALFacade dalFacade;
    private boolean isRunning = true;
    private final List<DataObserver> observers;
    private LocalDateTime lastReceivedUpdate;
    private int state;

    public ConcreteObservable(ObserverEvent e) {
        dalFacade = new DALManager();
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
                    setState(dalFacade.getAttendanceForLesson(e.getCourse().getId(), LocalDateTime.now()));
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

    public int getState() {
        return state;
    }

    private void setState(int newState) {
        state = newState;
    }

}