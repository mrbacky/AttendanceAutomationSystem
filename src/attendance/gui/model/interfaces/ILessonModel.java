package attendance.gui.model.interfaces;

import attendance.be.Lesson;
import attendance.be.User;
import java.time.LocalDate;
import javafx.collections.ObservableList;

/**
 *
 * @author rado
 */
public interface ILessonModel {

    void loadLessonsForToday(User student, LocalDate current);

    ObservableList<Lesson> getLessonsForToday();

    void registerAttendance(User student, Lesson lesson);

}
