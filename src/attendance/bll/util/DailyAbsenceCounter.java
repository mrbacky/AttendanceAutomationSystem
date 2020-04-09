package attendance.bll.util;

import attendance.be.Lesson;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Martin
 */
public class DailyAbsenceCounter {

    public List<XYChart.Data<String, Integer>> getWeekdayAbsence(List<Lesson> allAttendanceForStudent) {
        List<XYChart.Data<String, Integer>> weekdayAbsence = new ArrayList();

        int monday = 0;
        int tuesday = 0;
        int wednesday = 0;
        int thursday = 0;
        int friday = 0;
        
        for (Lesson lesson : allAttendanceForStudent) {
            if (lesson.getStartTime().getDayOfWeek() == DayOfWeek.MONDAY & lesson.getStatusType() == Lesson.StatusType.ABSENT) {
                monday++;
            }
            if (lesson.getStartTime().getDayOfWeek() == DayOfWeek.TUESDAY & lesson.getStatusType() == Lesson.StatusType.ABSENT) {
                tuesday++;
            }
            if (lesson.getStartTime().getDayOfWeek() == DayOfWeek.WEDNESDAY & lesson.getStatusType() == Lesson.StatusType.ABSENT) {
                wednesday++;
            }
            if (lesson.getStartTime().getDayOfWeek() == DayOfWeek.THURSDAY & lesson.getStatusType() == Lesson.StatusType.ABSENT) {
                thursday++;
            }
            if (lesson.getStartTime().getDayOfWeek() == DayOfWeek.FRIDAY & lesson.getStatusType() == Lesson.StatusType.ABSENT) {
                friday++;
            }
        }

        weekdayAbsence.add(new XYChart.Data<>("Monday", monday));
        weekdayAbsence.add(new XYChart.Data<>("Tuesday", tuesday));
        weekdayAbsence.add(new XYChart.Data<>("Wednesday", wednesday));
        weekdayAbsence.add(new XYChart.Data<>("Thursday", thursday));
        weekdayAbsence.add(new XYChart.Data<>("Friday", friday));
        
        return weekdayAbsence;
    }

}
