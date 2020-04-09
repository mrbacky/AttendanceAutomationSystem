package attendance.bll;

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
