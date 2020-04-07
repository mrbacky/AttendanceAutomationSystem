package attendance.bll.util;

import attendance.be.Course;

/**
 * Also known as the subject.
 *
 * @author annem
 */
public interface DataObservable {

    void attach(DataObserver o);

    void detach(DataObserver o);

    void notifyObserver(Course c);

}
