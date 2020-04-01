package attendance.bll.util;

/**
 *
 * @author annem
 */
public class AbsencePercentageCalculator {

    public int calculateAbsence(int absentLessons, int conductedLessons) {
        int absencePercentage = (int) Math.round((double) 100 * absentLessons / conductedLessons);
        System.out.println("calulatorrrrrrrrrrrrrrrr" + absentLessons);
        System.out.println("calulatorrrrrrrrrrrrrrrr" + conductedLessons);
        System.out.println("calulatorrrrrrrrrrrrrrrr" + absencePercentage);
        return absencePercentage;
    }

}
