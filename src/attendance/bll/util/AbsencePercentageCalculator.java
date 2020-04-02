package attendance.bll.util;

/**
 *
 * @author annem
 */
public class AbsencePercentageCalculator {

    public int calculatePercentage(int absentLessons, int conductedLessons) {
        int absencePercentage = (int) Math.round((double) 100 * absentLessons / conductedLessons);
        return absencePercentage;
    }

}
