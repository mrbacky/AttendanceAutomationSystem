

package attendance.gui.model;

import attendance.be.Lesson;
import attendance.be.Lesson.StatusType;
import attendance.be.Student;
import attendance.bll.LogicManager;
import attendance.dal.Mock.MockCourseCalDAO;
import attendance.dal.Mock.MockStudentDAO;
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
    private final MockCourseCalDAO mockCourseCalDAO;
    private LogicManager logicManager;

    public static LessonModel getInstance() {
        if (lessonModel == null) {
            lessonModel = new LessonModel();
        }
        return lessonModel;
    }

    private LessonModel() {
        mockCourseCalDAO = new MockCourseCalDAO();
        logicManager = new LogicManager();
        

    }

    public void loadAllLessons(int userId, LocalDate current) {// calculate absence here
        List<Lesson> allLessons = logicManager.getLessonsForToday(userId, current);
        lessonList.clear();
        lessonList.addAll(allLessons);
        
    }

    public ObservableList<Lesson> getObsLessons() {
        return lessonList;
    }

    public void createRecord(int userId, int lessonId, StatusType status) {
        logicManager.createRecord(userId,lessonId,status);
    }
}
