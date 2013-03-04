package org.jfree.date;

import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.jfree.date.Day.*;
import static org.jfree.date.Day.FRIDAY;
import static org.jfree.date.Day.SATURDAY;

public class DayTest {

    /**
     * Test the conversion of a string to a weekday.  Note that this test will fail if the
     * default locale doesn't use English weekday names...devise a better test!
     */
    @Test
    public void testStringToWeekday() {

        Assert.assertEquals(Day.WEDNESDAY, Day.parse("Wednesday"));
        Assert.assertEquals(Day.WEDNESDAY, Day.parse("Wed"));
    }

    @Test
    public void stringToWeekdayShouldIgnoreWhitespace() {

        Assert.assertEquals(Day.WEDNESDAY, Day.parse(" Wednesday "));
        Assert.assertEquals(Day.WEDNESDAY, Day.parse(" Wed "));
    }

    @Test
    public void stringToWeekdayShouldReturnNegativeOneForUnparseableStrings() {
        try {
            Day.parse("Wad");
            Assert.fail("Invalid weekday code should throw exception");
        } catch (IllegalArgumentException e) {
        }
        try {
            Day.parse("Wadnesday");
            Assert.fail("Invalid weekday code should throw exception");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testIsValidWeekdayCode() throws Exception {
        for (int day = 1; day <= 7; day++)
            Day.fromInt(day);

        try {
            Day.fromInt(0);
            fail("Invalid day code should throw exception");
        } catch (IllegalArgumentException e) {
        }
        try {
            Day.fromInt(8);
            fail("Invalid day code should throw exception");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testStringToWeekdayCode() throws Exception {
        try {
            Day.parse("Hello");
            fail("Invalid day code should throw exception");
        } catch (IllegalArgumentException e) {
        }
        assertEquals(MONDAY, Day.parse("Monday"));
        assertEquals(MONDAY, Day.parse("Mon"));
        assertEquals(MONDAY, Day.parse("monday"));
        assertEquals(MONDAY, Day.parse("MONDAY"));
        assertEquals(MONDAY, Day.parse("mon"));
        assertEquals(TUESDAY, Day.parse("Tuesday"));
        assertEquals(TUESDAY, Day.parse("Tue"));
        assertEquals(TUESDAY, Day.parse("tuesday"));
        assertEquals(TUESDAY, Day.parse("TUESDAY"));
        assertEquals(TUESDAY, Day.parse("tue"));
        assertEquals(WEDNESDAY, Day.parse("Wednesday"));
        assertEquals(WEDNESDAY, Day.parse("Wed"));
        assertEquals(WEDNESDAY, Day.parse("wednesday"));
        assertEquals(WEDNESDAY, Day.parse("WEDNESDAY"));
        assertEquals(WEDNESDAY, Day.parse("wed"));
        assertEquals(THURSDAY, Day.parse("Thursday"));
        assertEquals(THURSDAY, Day.parse("Thu"));
        assertEquals(THURSDAY, Day.parse("thursday"));
        assertEquals(THURSDAY, Day.parse("THURSDAY"));
        assertEquals(THURSDAY, Day.parse("thu"));
        assertEquals(FRIDAY, Day.parse("Friday"));
        assertEquals(FRIDAY, Day.parse("Fri"));
        assertEquals(FRIDAY, Day.parse("friday"));
        assertEquals(FRIDAY, Day.parse("FRIDAY"));
        assertEquals(FRIDAY, Day.parse("fri"));
        assertEquals(SATURDAY, Day.parse("Saturday"));
        assertEquals(SATURDAY, Day.parse("Sat"));
        assertEquals(SATURDAY, Day.parse("saturday"));
        assertEquals(SATURDAY, Day.parse("SATURDAY"));
        assertEquals(SATURDAY, Day.parse("sat"));
        assertEquals(SUNDAY, Day.parse("Sunday"));
        assertEquals(SUNDAY, Day.parse("Sun"));
        assertEquals(SUNDAY, Day.parse("sunday"));
        assertEquals(SUNDAY, Day.parse("SUNDAY"));
        assertEquals(SUNDAY, Day.parse("sun"));
    }

    @Test
    public void testWeekdayCodeToString() throws Exception {
        assertEquals("Sunday", SUNDAY.toString());
        assertEquals("Monday", MONDAY.toString());
        assertEquals("Tuesday", TUESDAY.toString());
        assertEquals("Wednesday", WEDNESDAY.toString());
        assertEquals("Thursday", THURSDAY.toString());
        assertEquals("Friday", FRIDAY.toString());
        assertEquals("Saturday", SATURDAY.toString());
    }
}