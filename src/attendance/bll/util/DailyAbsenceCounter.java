/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    
//    private int monday;
//    private int tuesday;
//    private int wednesday;
//    private int thursday;
//    private int friday;
    List<Lesson> cc;
    public ArrayList getWeekdayAbsence (List<Lesson> cc){
        ArrayList weeklyAbsence = new ArrayList();
        int monday =0;
        int tuesday =0;
        int wednesday=0;
        int thursday=0;
        int friday=0;
        for (Lesson lesson : cc) {
            DayOfWeek a = lesson.getStartTime().getDayOfWeek();
            if(lesson.getStartTime().getDayOfWeek() == DayOfWeek.MONDAY & lesson.getStatusType() == Lesson.StatusType.ABSENT){
                monday++;
            }
            if(lesson.getStartTime().getDayOfWeek() == DayOfWeek.TUESDAY & lesson.getStatusType() == Lesson.StatusType.ABSENT){
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
        weeklyAbsence.add(monday);
        weeklyAbsence.add(tuesday);
        weeklyAbsence.add(wednesday);
        weeklyAbsence.add(thursday);
        weeklyAbsence.add(friday);
        return weeklyAbsence;
    }
    
}
