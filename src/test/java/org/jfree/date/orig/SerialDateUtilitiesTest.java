package org.jfree.date.orig;

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
        final SerialDate d1 = SerialDate.createInstance(1, MonthConstants.APRIL, 2002);
        final SerialDate d2 = SerialDate.createInstance(2, MonthConstants.APRIL, 2002);
        final int count = SerialDateUtilities.dayCountActual(d1, d2);
        assertEquals(1, count);
    }

    /**
     * Problem 30/360 day count.
     */
    @Test
    public void testDayCount30() {
        final SerialDate d1 = SerialDate.createInstance(1, MonthConstants.APRIL, 2002);
        final SerialDate d2 = SerialDate.createInstance(2, MonthConstants.APRIL, 2002);
        final int count = SerialDateUtilities.dayCount30(d1, d2);
        assertEquals(1, count);
    }

    /**
     * Problem 30/360ISDA day count.
     */
    @Test
    public void testDayCount30ISDA() {
        final SerialDate d1 = SerialDate.createInstance(1, MonthConstants.APRIL, 2002);
        final SerialDate d2 = SerialDate.createInstance(2, MonthConstants.APRIL, 2002);
        final int count = SerialDateUtilities.dayCount30ISDA(d1, d2);
        assertEquals(1, count);
    }

    /**
     * Problem 30/360PSA day count.
     */
    @Test
    public void testDayCount30PSA() {
        final SerialDate d1 = SerialDate.createInstance(1, MonthConstants.APRIL, 2002);
        final SerialDate d2 = SerialDate.createInstance(2, MonthConstants.APRIL, 2002);
        final int count = SerialDateUtilities.dayCount30PSA(d1, d2);
        assertEquals(1, count);
    }

    /**
     * Problem 30E/360 day count.
     */
    @Test
    public void testDayCount3030E() {
        final SerialDate d1 = SerialDate.createInstance(1, MonthConstants.APRIL, 2002);
        final SerialDate d2 = SerialDate.createInstance(2, MonthConstants.APRIL, 2002);
        final int count = SerialDateUtilities.dayCount30E(d1, d2);
        assertEquals(1, count);
    }

}
