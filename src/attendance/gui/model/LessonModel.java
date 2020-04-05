package attendance.gui.model;

import attendance.be.Lesson;
import attendance.bll.LogicFacade;
import attendance.bll.LogicManager;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author rado
 */
public class LessonModel {

    private static LessonModel lessonModel;
    private final ObservableList<Lesson> lessonList = FXCollections.observableArrayList();
    private LogicFacade logicManager;

    public static LessonModel getInstance() {
        if (lessonModel == null) {
            lessonModel = new LessonModel();
        }
        return lessonModel;
    }

    private LessonModel() {
        logicManager = new LogicManager();
    }

    public void loadAllLessons(int userId, LocalDate current) {// calculate absence here
        List<Lesson> allLessons = logicManager.getLessonsForToday(userId, current);
        lessonList.clear();
        lessonList.addAll(allLessons);
        System.out.println("print from lesson model > Lessons for student : " + allLessons);

    }

    public ObservableList<Lesson> getObsLessons() {
        return lessonList;
    }

    public void createRecord(int userId, Lesson lessonToInsert) {
        logicManager.createRecord(userId, lessonToInsert);
        int index = lessonList.indexOf(lessonToInsert);
        lessonList.set(index, lessonToInsert);
    }
}
