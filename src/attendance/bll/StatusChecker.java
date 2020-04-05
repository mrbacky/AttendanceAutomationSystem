package attendance.bll;

import attendance.be.Lesson;
import attendance.gui.model.LessonModel;
import attendance.gui.model.ModelException;
import attendance.gui.model.UserModel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rado
 */
public class StatusChecker {

    private static StatusChecker statusChecker;

    public static StatusChecker getInstance() {
        if (statusChecker == null) {
            statusChecker = new StatusChecker();
        }
        return statusChecker;
    }

    private List<Lesson> lessonList = LessonModel.getInstance().getObsLessons();
    boolean absentStatus = false;

    

}
