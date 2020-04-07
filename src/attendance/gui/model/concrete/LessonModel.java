package attendance.gui.model.concrete;

import attendance.be.Lesson;
import attendance.bll.BLLManager;
import attendance.bll.util.AbsenceCounter;
import attendance.bll.util.AbsencePercentageCalculator;
import attendance.gui.model.interfaces.ILessonModel;
import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import attendance.bll.IBLLFacade;

/**
 *
 * @author rado
 */
public class LessonModel implements ILessonModel {

    private static LessonModel lessonModel;
    private final IBLLFacade bllManager;
    private final ObservableList<Lesson> lessonList = FXCollections.observableArrayList();
    private final ObservableList<Lesson> recordsList = FXCollections.observableArrayList();
    private final IntegerProperty absencePercentageLabel = new SimpleIntegerProperty();
    private final AbsenceCounter aCounter;
    private final AbsencePercentageCalculator aCalc;

    public LessonModel(IBLLFacade bllManager) {
        this.bllManager = bllManager;
        aCounter = new AbsenceCounter();
        aCalc = new AbsencePercentageCalculator();
    }

    /**
     *
     * @param userId
     * @param current
     */
    @Override
    public void loadAllLessons(int userId, LocalDate current) {// calculate absence here
        List<Lesson> allLessons = bllManager.getLessonsForToday(userId, current);
        lessonList.clear();
        lessonList.addAll(allLessons);
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<Lesson> getObsLessons() {
        return lessonList;
    }

    /**
     *
     * @param userId
     * @param lessonToInsert
     */
    @Override
    public void createRecord(int userId, Lesson lessonToInsert) {
        bllManager.createRecord(userId, lessonToInsert);
        int index = lessonList.indexOf(lessonToInsert);
        lessonList.set(index, lessonToInsert);
    }

    /**
     *
     * @param userId
     */
    @Override
    public void loadAllRecords(int userId) {
        List<Lesson> allRecords = bllManager.getAttendanceRecordsForAllCourses(userId);
        for (Lesson record : allRecords) {
            record.setDay();
            record.setDate();
            record.setTimeFrame();
        }
        recordsList.clear();
        recordsList.addAll(allRecords);
        absencePercentageLabel.setValue(calculateAbsenceLabel(allRecords));
        System.out.println("loadAllRecords: " + absencePercentageLabel.getValue());
    }

    /**
     *
     * @return
     */
    @Override
    public ObservableList<Lesson> getObsRecords() {
        return recordsList;
    }

    /**
     *
     * @param userId
     * @param courseId
     */
    @Override
    public void filterByCourse(int userId, int courseId) {
        List<Lesson> temp = bllManager.getAttendanceRecordsForACourse(userId, courseId);
        for (Lesson record : temp) {
            record.setDay();
            record.setDate();
            record.setTimeFrame();
        }
        recordsList.clear();
        recordsList.addAll(temp);
        absencePercentageLabel.setValue(calculateAbsenceLabel(temp));
        System.out.println("filterByCourse" + absencePercentageLabel.getValue());
    }

    /**
     *
     * @param list
     * @return
     */
    @Override
    public int calculateAbsenceLabel(List<Lesson> list) {
        int absence = aCounter.count(list);
        int h = list.size();
        System.out.println("list size: " + h);
        int t = aCalc.calculatePercentage(absence, h);
        System.out.println("int label: " + t);
        return t;
    }

    /**
     *
     * @return
     */
    @Override
    public int getAbsencePercentageLabel() {
        return absencePercentageLabel.get();
    }

    /**
     *
     * @param value
     */
    @Override
    public void setAbsencePercentageLabel(int value) {
        absencePercentageLabel.set(value);
    }

    /**
     *
     * @return
     */
    @Override
    public IntegerProperty absencePercentageLabelProperty() {
        return absencePercentageLabel;
    }
}
