package attendance.bll.observable;

import attendance.bll.observer.DataObserver;
import attendance.bll.util.ObserverEvent;

/**
 * Also known as the subject.
 *
 * @author annem
 */
public interface DataObservable {

    void attach(DataObserver o);

    void detach(DataObserver o);

    void notifyObserver(ObserverEvent e);

}
