/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.bll.util;

import attendance.be.Lesson;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rados
 */
public class AbsenceCounterTest {

    public AbsenceCounterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of countAbsentLessons method, of class AbsenceCounter.
     */
    @Test
    public void testCountAbsentLessons() {
        System.out.println("countAbsentLessons");
        List<Lesson> lessons = new ArrayList<>();
        lessons.add(new Lesson(78, "SDE", LocalDateTime.parse("2020-04-14T09:00:00"), LocalDateTime.parse("2020-04-14T11:30:00"), Lesson.StatusType.ABSENT));
        lessons.add(new Lesson(35, "SCO", LocalDateTime.parse("2020-04-14T09:00:00"), LocalDateTime.parse("2020-04-14T11:30:00"), Lesson.StatusType.PRESENT));
        lessons.add(new Lesson(6, "ITO", LocalDateTime.parse("2020-04-14T09:00:00"), LocalDateTime.parse("2020-04-14T11:30:00"), Lesson.StatusType.PRESENT));
        lessons.add(new Lesson(7, "DBOS", LocalDateTime.parse("2020-04-14T09:00:00"), LocalDateTime.parse("2020-04-14T11:30:00"), Lesson.StatusType.PRESENT));
        lessons.add(new Lesson(8, "ITO", LocalDateTime.parse("2020-04-14T09:00:00"), LocalDateTime.parse("2020-04-14T11:30:00"), Lesson.StatusType.ABSENT));
        lessons.add(new Lesson(32, "ITO", LocalDateTime.parse("2020-04-14T09:00:00"), LocalDateTime.parse("2020-04-14T11:30:00"), Lesson.StatusType.ABSENT));
        lessons.add(new Lesson(45, "SDE", LocalDateTime.parse("2020-12-14T09:00:00"), LocalDateTime.parse("2020-04-14T11:30:00"), Lesson.StatusType.ABSENT));
        lessons.add(new Lesson(783, "ITO", LocalDateTime.parse("2020-04-14T09:00:00"), LocalDateTime.parse("2020-04-14T11:30:00"), Lesson.StatusType.ABSENT));
        lessons.add(new Lesson(38, "ITO", LocalDateTime.parse("2020-02-14T09:00:00"), LocalDateTime.parse("2020-04-14T11:30:00"), Lesson.StatusType.PRESENT));
        lessons.add(new Lesson(1, "ITO", LocalDateTime.parse("2020-11-14T09:00:00"), LocalDateTime.parse("2020-04-14T11:30:00"), Lesson.StatusType.PRESENT));
        lessons.add(new Lesson(14, "ITO", LocalDateTime.parse("2020-01-14T09:00:00"), LocalDateTime.parse("2020-04-14T11:30:00"), Lesson.StatusType.ABSENT));

        AbsenceCounter instance = new AbsenceCounter();
        int expResult = 6;
        int result = instance.countAbsentLessons(lessons);
        assertEquals(expResult, result);
    }

}
