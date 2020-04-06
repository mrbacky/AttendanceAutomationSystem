package attendance.bll.util;

import attendance.be.Lesson;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin
 */
public class DailyAbsenceCounter {

    public List<Integer> getWeekdayAbsence(List<Lesson> allAttendanceForStudent) {
        List<Integer> weekdayAbsence = new ArrayList();

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

        weekdayAbsence.add(monday);
        weekdayAbsence.add(tuesday);
        weekdayAbsence.add(wednesday);
        weekdayAbsence.add(thursday);
        weekdayAbsence.add(friday);
        return weekdayAbsence;
    }

}
