package attendance.gui.model.concrete;

import attendance.be.Lesson;
import attendance.be.User;
import attendance.gui.model.interfaces.ILessonModel;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import attendance.bll.IBLLFacade;
import java.util.Collections;
import java.util.Comparator;

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

    @Override
    public void loadLessonsForToday(User student, LocalDate current) {
        List<Lesson> allLessons = bllManager.getLessonsForToday(student, current);
        Collections.sort(allLessons, (Lesson o1, Lesson o2) -> {
            return o1.getStartTime().compareTo(o2.getStartTime());
        });
        lessonList.clear();
        lessonList.addAll(allLessons);
        System.out.println("lesson  list: " + lessonList);
    }

    @Override
    public ObservableList<Lesson> getLessonsForToday() {

        return lessonList;
    }

    @Override
    public void registerAttendance(User student, Lesson lesson) {
        bllManager.createRecord(student, lesson);
        int index = lessonList.indexOf(lesson);
        lessonList.set(index, lesson);
    }

}
