package org.jfree.date;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Some tests for the SerialDateUtilities class.
 */
public class SerialDateUtilitiesTest {

    /**
     * Problem actual day count.
     */
    @Test
    public void testDayCountActual() {
        final DayDate d1 = DayDate.createInstance(1, Month.APRIL, 2002);
        final DayDate d2 = DayDate.createInstance(2, Month.APRIL, 2002);
        final int count = SerialDateUtilities.dayCountActual(d1, d2);
        assertEquals(1, count);
    }

    /**
     * Problem 30/360 day count.
     */
    @Test
    public void testDayCount30() {
        final DayDate d1 = DayDate.createInstance(1, Month.APRIL, 2002);
        final DayDate d2 = DayDate.createInstance(2, Month.APRIL, 2002);
        final int count = SerialDateUtilities.dayCount30(d1, d2);
        assertEquals(1, count);
    }

    /**
     * Problem 30/360ISDA day count.
     */
    @Test
    public void testDayCount30ISDA() {
        final DayDate d1 = DayDate.createInstance(1, Month.APRIL, 2002);
        final DayDate d2 = DayDate.createInstance(2, Month.APRIL, 2002);
        final int count = SerialDateUtilities.dayCount30ISDA(d1, d2);
        assertEquals(1, count);
    }

    /**
     * Problem 30/360PSA day count.
     */
    @Test
    public void testDayCount30PSA() {
        final DayDate d1 = DayDate.createInstance(1, Month.APRIL, 2002);
        final DayDate d2 = DayDate.createInstance(2, Month.APRIL, 2002);
        final int count = SerialDateUtilities.dayCount30PSA(d1, d2);
        assertEquals(1, count);
    }

    /**
     * Problem 30E/360 day count.
     */
    @Test
    public void testDayCount3030E() {
        final DayDate d1 = DayDate.createInstance(1, Month.APRIL, 2002);
        final DayDate d2 = DayDate.createInstance(2, Month.APRIL, 2002);
        final int count = SerialDateUtilities.dayCount30E(d1, d2);
        assertEquals(1, count);
    }

}
