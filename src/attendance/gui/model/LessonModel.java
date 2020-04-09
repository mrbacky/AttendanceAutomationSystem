package attendance.gui.model;

import attendance.be.Course;
import attendance.be.Lesson;
import attendance.be.Student;
import attendance.bll.ConcreteObservable3;
import attendance.bll.ConcreteObservable4;
import attendance.bll.DataObserver;
import attendance.bll.LogicFacade;
import attendance.bll.LogicManager;
import attendance.bll.ObserverEvent;
import attendance.bll.util.AbsenceCounter;
import attendance.bll.util.AbsencePercentageCalculator;
import java.time.LocalDate;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

/**
 *
 * @author rado
 */
public class LessonModel {

    private static LessonModel lessonModel;
    private final LogicFacade logicManager;
    private final ObservableList<Lesson> lessonList = FXCollections.observableArrayList();
    private final ObservableList<Lesson> recordsList = FXCollections.observableArrayList();
    private final IntegerProperty absencePercentageLabel = new SimpleIntegerProperty();
    private final AbsenceCounter aCounter;
    private final AbsencePercentageCalculator aCalc;

    private final ObservableList<Lesson> studentLessonList = FXCollections.observableArrayList();
    private final ObservableList<XYChart.Data<String, Integer>> absencePerWeekday = FXCollections.observableArrayList();
    private ConcreteObservable3 bllComponent3;
    private ConcreteObservable4 bllComponent4;

    public static LessonModel getInstance() {
        if (lessonModel == null) {
            lessonModel = new LessonModel();
        }
        return lessonModel;
    }

    private LessonModel() {
        logicManager = new LogicManager();
        aCounter = new AbsenceCounter();
        aCalc = new AbsencePercentageCalculator();
    }

    public void loadAllLessons(int userId, LocalDate current) {// calculate absence here
        List<Lesson> allLessons = logicManager.getLessonsForToday(userId, current);
        lessonList.clear();

        lessonList.addAll(allLessons);
    }

//    public void loadAllStudenLessons(int userId, int id) {// calculate absence here
//        List<Lesson> allLessons = logicManager.getAttendanceRecordsForACourse(userId, id);
//        lessonList.clear();
//
//        lessonList.addAll(allLessons);
//    }
    public ObservableList<Lesson> getObsLessons() {
        return lessonList;
    }

    public ObservableList<Lesson> getObsStudentLessons() {
        return studentLessonList;
    }

    public ObservableList<XYChart.Data<String, Integer>> getObsWeekdayAbsenceCount() {
        return absencePerWeekday;
    }

    public void startObserving(Student s, Course c) {
        System.out.println("stud: " + s.getName());
        System.out.println("cour: " + c.getName());
        ObserverEvent e = new ObserverEvent(c, s);
        System.out.println("startObserving" + e.getCourse().getName() + e.getStudent().getName());
        bllComponent3 = new ConcreteObservable3(e);
        bllComponent4 = new ConcreteObservable4(e);
        DataObserver observer = new DataObserver() {
            @Override
            public void update(ObserverEvent e) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("HEREEEEEEEEEEEe");
                        List<Lesson> l = bllComponent3.getState();
                        if (l != null) {
                            studentLessonList.setAll(l);
                        }
                        List<XYChart.Data<String, Integer>> i = bllComponent4.getState();
                        if (i != null) {
                            absencePerWeekday.setAll(i);
                            for (XYChart.Data<String, Integer> data : i) {                                                           
                                System.out.println("Concrete4: startObserving " + data.getXValue() +" "+ data.getYValue());                    
                            }
                            System.out.println("absencePerWeekday: " + absencePerWeekday.get(0));
                            System.out.println("absencePerWeekday: " + absencePerWeekday.get(1));
                            System.out.println("absencePerWeekday: " + absencePerWeekday.get(2));
                            System.out.println("absencePerWeekday: " + absencePerWeekday.get(3));
                            System.out.println("absencePerWeekday: " + absencePerWeekday.get(4));
                        }
                    }
                });
            }
        };
        bllComponent3.attach(observer);
        bllComponent4.attach(observer);
    }

    public void stopObserving() {
        bllComponent3.setIsRunning(false);
        bllComponent4.setIsRunning(false);
    }

    public void createRecord(int userId, Lesson lessonToInsert) {
        logicManager.createRecord(userId, lessonToInsert);
        int index = lessonList.indexOf(lessonToInsert);
        lessonList.set(index, lessonToInsert);
    }

    public void loadAllRecords(int userId) {
        List<Lesson> allRecords = logicManager.getAttendanceRecordsForAllCourses(userId);
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

    public ObservableList<Lesson> getObsRecords() {
        return recordsList;
    }

    public void filterByCourse(int userId, int courseId) {
        List<Lesson> temp = logicManager.getAttendanceRecordsForACourse(userId, courseId);
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

    private int calculateAbsenceLabel(List<Lesson> list) {
        int absence = aCounter.count(list);
        int h = list.size();
        System.out.println("list size: " + h);
        int t = aCalc.calculatePercentage(absence, h);
        System.out.println("int label: " + t);
        return t;
    }

    public int getAbsencePercentageLabel() {
        return absencePercentageLabel.get();
    }

    public void setAbsencePercentageLabel(int value) {
        absencePercentageLabel.set(value);
    }

    public IntegerProperty absencePercentageLabelProperty() {
        return absencePercentageLabel;
    }
}
