package attendance.bll.util;

import attendance.be.Lesson;
import java.util.List;

/**
 *
 * @author annem
 */
public class AbsenceCounter {

    public int countAbsentLessons(List<Lesson> lessons) {
        int count = 0;
        for (Lesson lesson : lessons) {
            if (lesson.getStatusType() == Lesson.StatusType.ABSENT) {
                count++;
            }
        }
        return count;
    }

}
