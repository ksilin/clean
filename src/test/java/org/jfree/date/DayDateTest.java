package org.jfree.date;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Calendar;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


/**
 * Some JUnit tests for the {@link DayDate} class.
 */
public class DayDateTest {

    /**
     * Date representing November 9.
     */
    private DayDate nov9Y2001;

    /**
     * Problem set up.
     */
    @Before
    public void setUp() {
        this.nov9Y2001 = DayDate.createInstance(9, Month.NOVEMBER, 2001);
    }

    /**
     * 9 Nov 2001 plus two months should be 9 Jan 2002.
     */
    @Test
    public void testAddMonthsTo9Nov2001() {
        final DayDate jan9Y2002 = DayDate.addMonths(2, this.nov9Y2001);
        final DayDate answer = DayDate.createInstance(9, Month.JANUARY, 2002);
        assertEquals(answer, jan9Y2002);
    }

    /**
     * A test case for a reported bug, now fixed.
     */
    @Test
    public void testAddMonthsTo5Oct2003() {
        final DayDate d1 = DayDate.createInstance(5, Month.OCTOBER, 2003);
        final DayDate d2 = DayDate.addMonths(2, d1);
        assertEquals(d2, DayDate.createInstance(5, Month.DECEMBER, 2003));
    }

    /**
     * A test case for a reported bug, now fixed.
     */
    @Test
    public void testAddMonthsTo1Jan2003() {
        final DayDate d1 = DayDate.createInstance(1, Month.JANUARY, 2003);
        final DayDate d2 = DayDate.addMonths(0, d1);
        assertEquals(d2, d1);
    }

    /**
     * Monday preceding Friday 9 November 2001 should be 5 November.
     */
    @Test
    public void testMondayPrecedingFriday9Nov2001() {
        DayDate mondayBefore = DayDate.getPreviousDayOfWeek(
                Calendar.MONDAY, this.nov9Y2001
        );
        assertEquals(5, mondayBefore.getDayOfMonth());
    }

    /**
     * Monday following Friday 9 November 2001 should be 12 November.
     */
    @Test
    public void testMondayFollowingFriday9Nov2001() {
        DayDate mondayAfter = DayDate.getFollowingDayOfWeek(
                Calendar.MONDAY, this.nov9Y2001
        );
        assertEquals(12, mondayAfter.getDayOfMonth());
    }

    /**
     * Monday nearest Friday 9 November 2001 should be 12 November.
     */
    @Test
    public void testMondayNearestFriday9Nov2001() {
        DayDate mondayNearest = DayDate.getNearestDayOfWeek(
                Calendar.MONDAY, this.nov9Y2001
        );
        assertEquals(12, mondayNearest.getDayOfMonth());
    }

    /**
     * The Monday nearest to 22nd January 1970 falls on the 19th.
     */
    @Test
    public void testMondayNearest22Jan1970() {
        DayDate jan22Y1970 = DayDate.createInstance(22, Month.JANUARY, 1970);
        DayDate mondayNearest = DayDate.getNearestDayOfWeek(Calendar.MONDAY, jan22Y1970);
        assertEquals(19, mondayNearest.getDayOfMonth());
    }

    @Test
    public void testMonthCodeToQuarter(){

        assertEquals(1, DayDate.monthCodeToQuarter(Month.JANUARY));
        assertEquals(4, DayDate.monthCodeToQuarter(Month.DECEMBER));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMonthCodeToQuarterShouldThrowOn0(){

        Month.make(0);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testMonthCodeToQuarterShouldThrowOn13(){

        Month.make(13);
    }

    /**
     * Problem that the conversion of days to strings returns the right result.  Actually, this
     * result depends on the Locale so this test needs to be modified.
     */
    @Test
    public void testWeekdayCodeToString() {

        final String test = DayDate.weekdayCodeToString(Calendar.SATURDAY);
        assertEquals("Saturday", test);
    }


    /**
     * Test the conversion of a string to a weekday.  Note that this test will fail if the
     * default locale doesn't use English weekday names...devise a better test!
     */
    @Test
    public void testStringToWeekday() {

        int weekday = DayDate.stringToWeekdayCode("Wednesday");
        assertEquals(DayDate.WEDNESDAY, weekday);

        weekday = DayDate.stringToWeekdayCode("Wed");
        assertEquals(DayDate.WEDNESDAY, weekday);
    }

    @Test
    public void stringToWeekdayShouldIgnoreWhitespace() {

        int weekday = DayDate.stringToWeekdayCode(" Wednesday ");
        assertEquals(Calendar.WEDNESDAY, weekday);

        weekday = DayDate.stringToWeekdayCode(" Wed ");
        assertEquals(Calendar.WEDNESDAY, weekday);
    }

    @Test
    public void stringToWeekdayShouldReturnNegativeOneForUnparseableStrings() {

        int weekday = DayDate.stringToWeekdayCode("Wad");
        assertEquals(-1, weekday);

        weekday = DayDate.stringToWeekdayCode("Wadnesday");
        assertEquals(-1, weekday);
    }

    /**
     * 1900 is not a leap year.
     */
    @Test
    public void testIsNotLeapYear1900() {
        assertTrue(!DayDate.isLeapYear(1900));
    }

    /**
     * 2000 is a leap year.
     */
    @Test
    public void testIsLeapYear2000() {
        assertTrue(DayDate.isLeapYear(2000));
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1899 is 0.
     */
    @Test
    public void testLeapYearCount1899() {
        assertEquals(DayDate.leapYearCount(1899), 0);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1903 is 0.
     */
    @Test
    public void testLeapYearCount1903() {
        assertEquals(DayDate.leapYearCount(1903), 0);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1904 is 1.
     */
    @Test
    public void testLeapYearCount1904() {
        assertEquals(DayDate.leapYearCount(1904), 1);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 1999 is 24.
     */
    @Test
    public void testLeapYearCount1999() {
        assertEquals(DayDate.leapYearCount(1999), 24);
    }

    /**
     * The number of leap years from 1900 up-to-and-including 2000 is 25.
     */
    @Test
    public void testLeapYearCount2000() {
        assertEquals(DayDate.leapYearCount(2000), 25);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {

        DayDate d1 = DayDate.createInstance(15,Month.APRIL, 2000);
        DayDate d2 = null;

        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(buffer);
            out.writeObject(d1);
            out.close();

            ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(buffer.toByteArray()));
            d2 = (DayDate) in.readObject();
            in.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        assertEquals(d1, d2);

    }

    /**
     * A test for bug report 1096282 (now fixed).
     */
    @Test
    public void test1096282() {
        DayDate d = DayDate.createInstance(29, Month.FEBRUARY, 2004);
        d = DayDate.addYears(1, d);
        DayDate expected = DayDate.createInstance(28, Month.FEBRUARY, 2005);
        assertTrue(d.isOn(expected));
    }

    /**
     * Miscellaneous tests for the addMonths() method.
     */
    @Test
    public void testAddMonths() {
        DayDate d1 = DayDate.createInstance(31, Month.MAY, 2004);

        DayDate d2 = DayDate.addMonths(1, d1);
        assertEquals(30, d2.getDayOfMonth());
        assertEquals(Month.JUNE, d2.getMonth());
        assertEquals(2004, d2.getYYYY());

        DayDate d3 = DayDate.addMonths(2, d1);
        assertEquals(31, d3.getDayOfMonth());
        assertEquals(Month.JULY, d3.getMonth());
        assertEquals(2004, d3.getYYYY());

        DayDate d4 = DayDate.addMonths(1, DayDate.addMonths(1, d1));
        assertEquals(30, d4.getDayOfMonth());
        assertEquals(Month.JULY, d4.getMonth());
        assertEquals(2004, d4.getYYYY());
    }
}
