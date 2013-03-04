package org.jfree.date;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Calendar;

import static junit.framework.Assert.assertEquals;


/**
 * Tests for the SpreadsheetDate class.
 */
public class SpreadsheetDateTest {

    /**
     * Date representing 1 January 1900.
     */
    private DayDate jan1Y1900;

    /**
     * Date representing serial day number 2.
     */
    private DayDate s2;

    /**
     * Setup.
     */
    @Before
    public void setUp() {
        this.jan1Y1900 = new SpreadsheetDate(1, Month.JANUARY, 1900);
        this.s2 = new SpreadsheetDate(2);
    }

    /**
     * 1 January 1900 is a Thursday.
     */
    @Test
    public void test1Jan1900GetDayOfWeek() {
        final int dayOfWeek = this.jan1Y1900.getDayOfWeek();
        assertEquals(Calendar.MONDAY, dayOfWeek);
    }

    /**
     * 12 November 2001 is a Monday.
     */
    @Test
    public void test12Nov2001GetDayOfWeek() {
        final DayDate nov12Y2001 = new SpreadsheetDate(12, Month.NOVEMBER, 2001);
        final int dayOfWeek = nov12Y2001.getDayOfWeek();
        assertEquals(Calendar.MONDAY, dayOfWeek);
    }

    /**
     * Day 2 is the first of the month.
     */
    @Test
    public void testS2GetDayOfMonth() {
        final int dayOfMonth = this.s2.getDayOfMonth();
        assertEquals(1, dayOfMonth);
    }

    /**
     * Day 2 is in January.
     */
    @Test
    public void testS2GetMonth() {
        final Month month = this.s2.getMonth();
        assertEquals(Month.JANUARY, month);
    }

    /**
     * Day 2 is in 1900.
     */
    @Test
    public void testS2GetYYYY() {
        final int year = this.s2.getYYYY();
        assertEquals(1900, year);
    }

    /**
     * Day 37986 is 31 Dec 2003.
     */
    @Test
    public void test37986() {
        final SpreadsheetDate d = new SpreadsheetDate(37986);
        assertEquals(31, d.getDayOfMonth());
        assertEquals(Month.DECEMBER, d.getMonth());
        assertEquals(2003, d.getYYYY());
    }

    /**
     * Day 37987 is 1 Jan 2004.
     */
    @Test
    public void test37987() {
        final SpreadsheetDate d = new SpreadsheetDate(37987);
        assertEquals(1, d.getDayOfMonth());
        assertEquals(Month.JANUARY, d.getMonth());
        assertEquals(2004, d.getYYYY());
    }

    /**
     * Day 38352 is 31 Dec 2004.
     */
    @Test
    public void test38352() {
        final SpreadsheetDate d = new SpreadsheetDate(38352);
        assertEquals(31, d.getDayOfMonth());
        assertEquals(Month.DECEMBER, d.getMonth());
        assertEquals(2004, d.getYYYY());
    }

    /**
     * Day 38353 is 1 Jan 2005.
     */
    @Test
    public void test38353() {
        final SpreadsheetDate d = new SpreadsheetDate(38353);
        assertEquals(1, d.getDayOfMonth());
        assertEquals(Month.JANUARY, d.getMonth());
        assertEquals(2005, d.getYYYY());
    }

    /**
     * Create a date for serial number 36584: it should be 28-Feb-2000.
     */
    @Test
    public void test36584() {
        final SpreadsheetDate d = new SpreadsheetDate(36584);
        assertEquals(28, d.getDayOfMonth());
        assertEquals(Month.FEBRUARY, d.getMonth());
        assertEquals(2000, d.getYYYY());
    }

    /**
     * Create a date for serial number 36585: it should be 29-Feb-2000.
     */
    @Test
    public void test36585() {
        final SpreadsheetDate d = new SpreadsheetDate(36585);
        assertEquals(29, d.getDayOfMonth());
        assertEquals(Month.FEBRUARY, d.getMonth());
        assertEquals(2000, d.getYYYY());
    }

    /**
     * Create a date for serial number 36586: it should be 1-Mar-2000.
     */
    @Test
    public void test36586() {
        final SpreadsheetDate d = new SpreadsheetDate(36586);
        assertEquals(1, d.getDayOfMonth());
        assertEquals(Month.MARCH, d.getMonth());
        assertEquals(2000, d.getYYYY());
    }

    /**
     * Create a date for 01-Jan-1900: the serial number should be 2.
     */
    @Test
    public void test01Jan1900ToSerial() {
        final int serial = this.jan1Y1900.toSerial();
        assertEquals(2, serial);
    }

    /**
     * Create a date for 28-Feb-1900: the serial number should be 60.
     */
    @Test
    public void test28Feb1900ToSerial() {
        final SpreadsheetDate d = new SpreadsheetDate(28, Month.FEBRUARY, 1900);
        assertEquals(60, d.toSerial());
    }

    /**
     * Create a date for 01-Mar-1900: the serial number should be 61.
     */
    @Test
    public void test01Mar1900ToSerial() {
        final SpreadsheetDate d = new SpreadsheetDate(1, Month.MARCH, 1900);
        assertEquals(61, d.toSerial());
    }

    /**
     * Create a date for 31-Dec-1999: the serial number should be 36525.
     */
    @Test
    public void test31Dec1999ToSerial() {
        final SpreadsheetDate d = new SpreadsheetDate(31, Month.DECEMBER, 1999);
        assertEquals(36525, d.toSerial());
    }

    /**
     * Create a date for 1-Jan-2000: the serial number should be 36526.
     */
    @Test
    public void test01Jan2000ToSerial() {
        final SpreadsheetDate d = new SpreadsheetDate(1, Month.JANUARY, 2000);
        assertEquals(36526, d.toSerial());
    }

    /**
     * Create a date for 31-Jan-2000: the serial number should be 36556.
     */
    @Test
    public void test31Jan2000ToSerial() {
        final SpreadsheetDate d = new SpreadsheetDate(31, Month.JANUARY, 2000);
        assertEquals(36556, d.toSerial());
    }

    /**
     * Create a date for 01-Feb-2000: the serial number should be 36557.
     */
    @Test
    public void test01Feb2000ToSerial() {
        final SpreadsheetDate d = new SpreadsheetDate(1, Month.FEBRUARY, 2000);
        assertEquals(36557, d.toSerial());
    }

    /**
     * Create a date for 28-Feb-2000: the serial number should be 36584.
     */
    @Test
    public void test28Feb2000ToSerial() {
        final SpreadsheetDate d = new SpreadsheetDate(28, Month.FEBRUARY, 2000);
        assertEquals(36584, d.toSerial());
    }

    /**
     * Create a date for 29-Feb-2000: the serial number should be 36585.
     */
    @Test
    public void test29feb2000ToSerial() {
        final SpreadsheetDate d = new SpreadsheetDate(29, Month.FEBRUARY, 2000);
        assertEquals(36585, d.toSerial());
    }

    /**
     * Create a date for 1-Mar-2000: the serial number should be 36586.
     */
    @Test
    public void test1mar2000ToSerial() {
        final SpreadsheetDate d = new SpreadsheetDate(1, Month.MARCH, 2000);
        assertEquals(36586, d.toSerial());
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {

        final SpreadsheetDate d1 = new SpreadsheetDate(15, Month.APRIL, 2000);
        SpreadsheetDate d2 = null;

        try {
            final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            final ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(d1);
            out.close();

            final ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
            d2 = (SpreadsheetDate) in.readObject();
            in.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        assertEquals(d1, d2);

    }

}
