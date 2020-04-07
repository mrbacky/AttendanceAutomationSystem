/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.bll.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author rados
 */
public class AbsencePercentageCalculatorTest {
    
    public AbsencePercentageCalculatorTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of calculatePercentage method, of class AbsencePercentageCalculator.
     */
    @Test
    public void testCalculatePercentage() {
        System.out.println("calculatePercentage");
        int absentLessons = 0;
        int conductedLessons = 0;
        AbsencePercentageCalculator instance = new AbsencePercentageCalculator();
        int expResult = 0;
        int result = instance.calculatePercentage(absentLessons, conductedLessons);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
