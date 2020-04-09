package attendance.bll.observer;

import attendance.bll.util.ObserverEvent;

/**
 *
 * @author annem
 */
public interface DataObserver {

    void update(ObserverEvent e);

}
