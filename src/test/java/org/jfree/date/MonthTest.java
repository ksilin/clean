package org.jfree.date;

import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.jfree.date.Month.*;

public class MonthTest {
    public MonthTest() {
    }

    @Test
    public void testIsValidMonthCode() throws Exception {
        for (int i = 1; i <= 12; i++) {
            Month.fromInt(i);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMonthCode() throws Exception {
        Month.fromInt(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMonthCode2() throws Exception {
        Month.fromInt(13);
    }


    @Test
    public void testQuarter() throws Exception {
        Assert.assertEquals(1, Month.JANUARY.quarter());
        Assert.assertEquals(1, Month.FEBRUARY.quarter());
        Assert.assertEquals(1, Month.MARCH.quarter());
        Assert.assertEquals(2, Month.APRIL.quarter());
        Assert.assertEquals(2, Month.MAY.quarter());
        Assert.assertEquals(2, Month.JUNE.quarter());
        Assert.assertEquals(3, Month.JULY.quarter());
        Assert.assertEquals(3, Month.AUGUST.quarter());
        Assert.assertEquals(3, Month.SEPTEMBER.quarter());
        Assert.assertEquals(4, Month.OCTOBER.quarter());
        Assert.assertEquals(4, Month.NOVEMBER.quarter());
        Assert.assertEquals(4, Month.DECEMBER.quarter());
    }

    @Test
    public void testMonthCodeToString() throws Exception {
        Assert.assertEquals("January", JANUARY.toString());
        Assert.assertEquals("February", FEBRUARY.toString());
        Assert.assertEquals("March", MARCH.toString());
        Assert.assertEquals("April", APRIL.toString());
        Assert.assertEquals("May", MAY.toString());
        Assert.assertEquals("June", JUNE.toString());
        Assert.assertEquals("July", JULY.toString());
        Assert.assertEquals("August", AUGUST.toString());
        Assert.assertEquals("September", SEPTEMBER.toString());
        Assert.assertEquals("October", OCTOBER.toString());
        Assert.assertEquals("November", NOVEMBER.toString());
        Assert.assertEquals("December", DECEMBER.toString());
        Assert.assertEquals("Jan", JANUARY.toShortString());
        Assert.assertEquals("Feb", FEBRUARY.toShortString());
        Assert.assertEquals("Mar", MARCH.toShortString());
        Assert.assertEquals("Apr", APRIL.toShortString());
        Assert.assertEquals("May", MAY.toShortString());
        Assert.assertEquals("Jun", JUNE.toShortString());
        Assert.assertEquals("Jul", JULY.toShortString());
        Assert.assertEquals("Aug", AUGUST.toShortString());
        Assert.assertEquals("Sep", SEPTEMBER.toShortString());
        Assert.assertEquals("Oct", OCTOBER.toShortString());
        Assert.assertEquals("Nov", NOVEMBER.toShortString());
        Assert.assertEquals("Dec", DECEMBER.toShortString());
    }

    @Test
    public void testMonthParse() throws Exception {
        Assert.assertEquals(JANUARY, Month.parse("1"));
        Assert.assertEquals(FEBRUARY, Month.parse("2"));
        Assert.assertEquals(MARCH, Month.parse("3"));
        Assert.assertEquals(APRIL, Month.parse("4"));
        Assert.assertEquals(MAY, Month.parse("5"));
        Assert.assertEquals(JUNE, Month.parse("6"));
        Assert.assertEquals(JULY, Month.parse("7"));
        Assert.assertEquals(AUGUST, Month.parse("8"));
        Assert.assertEquals(SEPTEMBER, Month.parse("9"));
        Assert.assertEquals(OCTOBER, Month.parse("10"));
        Assert.assertEquals(NOVEMBER, Month.parse("11"));
        Assert.assertEquals(DECEMBER, Month.parse("12"));

        for (Month m : Month.values()) {
            Assert.assertEquals(m, Month.parse(m.toString()));
            Assert.assertEquals(m, Month.parse(m.toString()));
        }

        Assert.assertEquals(JANUARY, Month.parse("jan"));
        Assert.assertEquals(FEBRUARY, Month.parse("feb"));
        Assert.assertEquals(MARCH, Month.parse("mar"));
        Assert.assertEquals(APRIL, Month.parse("apr"));
        Assert.assertEquals(MAY, Month.parse("may"));
        Assert.assertEquals(JUNE, Month.parse("jun"));
        Assert.assertEquals(JULY, Month.parse("jul"));
        Assert.assertEquals(AUGUST, Month.parse("aug"));
        Assert.assertEquals(SEPTEMBER, Month.parse("sep"));
        Assert.assertEquals(OCTOBER, Month.parse("oct"));
        Assert.assertEquals(NOVEMBER, Month.parse("nov"));
        Assert.assertEquals(DECEMBER, Month.parse("dec"));
        Assert.assertEquals(JANUARY, Month.parse("JAN"));
        Assert.assertEquals(FEBRUARY, Month.parse("FEB"));
        Assert.assertEquals(MARCH, Month.parse("MAR"));
        Assert.assertEquals(APRIL, Month.parse("APR"));
        Assert.assertEquals(MAY, Month.parse("MAY"));
        Assert.assertEquals(JUNE, Month.parse("JUN"));
        Assert.assertEquals(JULY, Month.parse("JUL"));
        Assert.assertEquals(AUGUST, Month.parse("AUG"));
        Assert.assertEquals(SEPTEMBER, Month.parse("SEP"));
        Assert.assertEquals(OCTOBER, Month.parse("OCT"));
        Assert.assertEquals(NOVEMBER, Month.parse("NOV"));
        Assert.assertEquals(DECEMBER, Month.parse("DEC"));
        Assert.assertEquals(JANUARY, Month.parse("january"));
        Assert.assertEquals(FEBRUARY, Month.parse("february"));
        Assert.assertEquals(MARCH, Month.parse("march"));
        Assert.assertEquals(APRIL, Month.parse("april"));
        Assert.assertEquals(MAY, Month.parse("may"));
        Assert.assertEquals(JUNE, Month.parse("june"));
        Assert.assertEquals(JULY, Month.parse("july"));
        Assert.assertEquals(AUGUST, Month.parse("august"));
        Assert.assertEquals(SEPTEMBER, Month.parse("september"));
        Assert.assertEquals(OCTOBER, Month.parse("october"));
        Assert.assertEquals(NOVEMBER, Month.parse("november"));
        Assert.assertEquals(DECEMBER, Month.parse("december"));
        Assert.assertEquals(JANUARY, Month.parse("JANUARY"));
        Assert.assertEquals(FEBRUARY, Month.parse("FEBRUARY"));
        Assert.assertEquals(MARCH, Month.parse("MAR"));
        Assert.assertEquals(APRIL, Month.parse("APRIL"));
        Assert.assertEquals(MAY, Month.parse("MAY"));
        Assert.assertEquals(JUNE, Month.parse("JUNE"));
        Assert.assertEquals(JULY, Month.parse("JULY"));
        Assert.assertEquals(AUGUST, Month.parse("AUGUST"));
        Assert.assertEquals(SEPTEMBER, Month.parse("SEPTEMBER"));
        Assert.assertEquals(OCTOBER, Month.parse("OCTOBER"));
        Assert.assertEquals(NOVEMBER, Month.parse("NOVEMBER"));
        Assert.assertEquals(DECEMBER, Month.parse("DECEMBER"));
    }

    @Test
    public void testFailParsingOnInvalidString() {
        try {
            Month.parse("0");
            fail("Invalid month code should throw exception");
        } catch (IllegalArgumentException e) {
        }
        try {
            Month.parse("13");
            fail("Invalid month code should throw exception");
        } catch (IllegalArgumentException e) {
        }
        try {
            Month.parse("Hello");
            fail("Invalid month code should throw exception");
        } catch (IllegalArgumentException e) {
        }
    }
}