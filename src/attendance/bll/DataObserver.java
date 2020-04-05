package attendance.bll;

import attendance.be.Course;
import java.time.LocalDateTime;

/**
 *
 * @author annem
 */
public interface DataObserver {

    void update(Course c);

}
