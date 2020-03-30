package attendance.bll.util;

/**
 *
 * @author rado
 */
public class AbsenceCalculator {

    public double calculateAbsence(int absentLessonsCount, int lessonConductedCount) {
        double absence = absentLessonsCount / lessonConductedCount * 100;
        return absence;
    }

}
