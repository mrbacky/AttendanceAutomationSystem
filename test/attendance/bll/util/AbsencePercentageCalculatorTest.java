/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.bll.util;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rados
 */
public class AbsencePercentageCalculatorTest {

    public AbsencePercentageCalculatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of calculatePercentage method, of class AbsencePercentageCalculator.
     */
    @Test
    public void testCalculatePercentage() {
        System.out.println("calculatePercentage");
        int absentLessons = 7;
        int conductedLessons = 10;
        AbsencePercentageCalculator instance = new AbsencePercentageCalculator();
        int expResult = 70;
        int result = instance.calculatePercentage(absentLessons, conductedLessons);
        assertEquals(expResult, result);

    }

    @Test
    public void testCalculatePercentage2() {
        System.out.println("calculatePercentage");
        int absentLessons = 45;
        int conductedLessons = 100;
        AbsencePercentageCalculator instance = new AbsencePercentageCalculator();
        int expResult = 45;
        int result = instance.calculatePercentage(absentLessons, conductedLessons);
        assertEquals(expResult, result);

    }

    @Test
    public void testCalculatePercentage3() {
        System.out.println("calculatePercentage");
        int absentLessons = 3;
        int conductedLessons = 17;
        AbsencePercentageCalculator instance = new AbsencePercentageCalculator();
        int expResult = 18;
        int result = instance.calculatePercentage(absentLessons, conductedLessons);
        assertEquals(expResult, result);

    }

}
