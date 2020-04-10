package attendance.gui.model.concrete;

import attendance.be.Lesson;
import attendance.be.User;
import attendance.gui.model.interfaces.ILessonModel;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import attendance.bll.IBLLFacade;

/**
 *
 * @author rado
 */
public class LessonModel implements ILessonModel {

    private final IBLLFacade bllManager;
    private final ObservableList<Lesson> lessonList = FXCollections.observableArrayList();

    public LessonModel(IBLLFacade bllManager) {
        this.bllManager = bllManager;
    }

    /**
     *
     * @param student
     * @param current
     */
    @Override
    public void loadLessonsForToday(User student, LocalDate current) {
        List<Lesson> allLessons = bllManager.getLessonsForToday(student, current);
        lessonList.clear();
        lessonList.addAll(allLessons);
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<Lesson> getLessonsForToday() {
        return lessonList;
    }

    /**
     *
     * @param student
     * @param lesson
     */
    @Override
    public void registerAttendance(User student, Lesson lesson) {
        bllManager.createRecord(student, lesson);
        int index = lessonList.indexOf(lesson);
        lessonList.set(index, lesson);
    }

}
