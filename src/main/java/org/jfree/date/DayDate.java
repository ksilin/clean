/* ========================================================================
 * JCommon : a free general purpose class library for the Java(tm) platform
 * ========================================================================
 *
 * (C) Copyright 2000-2005, by Object Refinery Limited and Contributors.
 * 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation; either version 2.1 of the License, or 
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, 
 * USA.
 */

package org.jfree.date;

import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * An abstract class that defines our requirements for manipulating dates.
 * <p/>
 * Requirement 1 : match at least what Excel does for dates;
 * Requirement 2 : class is immutable;
 * <p/>
 * Why not just use java.util.Date?  We will, when it makes sense.  At times,
 * java.util.Date can be *too* precise - it represents an instant in time,
 * accurate to 1/1000th of a second (with the date itself depending on the
 * time-zone).  Sometimes we just want to represent a particular day (e.g. 21
 * January 2015) without concerning ourselves about the time of day, or the
 * time-zone, or anything else.  That's what we've defined DayDate for.
 * <p/>
 * You can call getInstance() to get a concrete subclass of DayDate.
 *
 * @author David Gilbert
 */
public abstract class DayDate implements Comparable,
        Serializable {

    private static final int[] LAST_DAY_OF_MONTH =
            {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static final int FIRST_WEEK_IN_MONTH = 1;

    public static final int SECOND_WEEK_IN_MONTH = 2;

    public static final int THIRD_WEEK_IN_MONTH = 3;

    public static final int FOURTH_WEEK_IN_MONTH = 4;

    public static final int LAST_WEEK_IN_MONTH = 0;

    public static final int INCLUDE_NONE = 0;

    public static final int INCLUDE_FIRST = 1;

    public static final int INCLUDE_SECOND = 2;

    public static final int INCLUDE_BOTH = 3;

    /**
     * Useful constant for specifying a day of the week relative to a fixed
     * date.
     */
    public static final int PRECEDING = -1;

    /**
     * Useful constant for specifying a day of the week relative to a fixed
     * date.
     */
    public static final int NEAREST = 0;

    /**
     * Useful constant for specifying a day of the week relative to a fixed
     * date.
     */
    public static final int FOLLOWING = 1;

    private String description;

    private static final long serialVersionUID = -293716040467423637L;

    protected DayDate() {
    }

    /**
     * Returns true if the supplied integer code represents a valid
     * week-in-the-month, and false otherwise.
     *
     * @param code the code being checked for validity.
     * @return <code>true</code> if the supplied integer code represents a
     *         valid week-in-the-month.
     */
    public static boolean isValidWeekInMonthCode(final int code) {

        switch (code) {
            case FIRST_WEEK_IN_MONTH:
            case SECOND_WEEK_IN_MONTH:
            case THIRD_WEEK_IN_MONTH:
            case FOURTH_WEEK_IN_MONTH:
            case LAST_WEEK_IN_MONTH:
                return true;
            default:
                return false;
        }

    }

    /**
     * Determines whether or not the specified year is a leap year.
     *
     * @param yyyy the year (in the range 1900 to 9999).
     * @return <code>true</code> if the specified year is a leap year.
     */
    public static boolean isLeapYear(final int yyyy) {

        boolean fourth = yyyy % 4 == 0;
        boolean hundredth = yyyy % 100 == 0;
        boolean fourHundredth = yyyy % 400 == 0;

        return fourth && (!hundredth || fourHundredth);
    }

    /**
     * Returns the number of leap years from 1900 to the specified year
     * INCLUSIVE.
     * <p/>
     * Note that 1900 is not a leap year.
     *
     * @param yyyy the year (in the range 1900 to 9999).
     * @return the number of leap years from 1900 to the specified year.
     */
    public static int leapYearCount(final int yyyy) {

        final int leap4 = (yyyy - 1896) / 4;
        final int leap100 = (yyyy - 1800) / 100;
        final int leap400 = (yyyy - 1600) / 400;
        return leap4 - leap100 + leap400;

    }

    /**
     * Returns the number of the last day of the month, taking into account
     * leap years.
     *
     * @param month the month.
     * @param yyyy  the year (in the range 1900 to 9999).
     * @return the number of the last day of the month.
     */
    public static int lastDayOfMonth(final Month month, final int yyyy) {

        final int result = LAST_DAY_OF_MONTH[month.getIndex()];
        if (month != Month.FEBRUARY) {
            return result;
        } else if (isLeapYear(yyyy)) {
            return result + 1;
        } else {
            return result;
        }

    }

    /**
     * Creates a new date by adding the specified number of days to the base
     * date.
     *
     * @param days the number of days to add (can be negative).
     * @param base the base date.
     * @return a new date.
     */
    public static DayDate addDays(final int days, final DayDate base) {

        final int serialDayNumber = base.toSerial() + days;
        return DayDateFactory.makeDate(serialDayNumber);

    }

    /**
     * Creates a new date by adding the specified number of months to the base
     * date.
     * <p/>
     * If the base date is close to the end of the month, the day on the result
     * may be adjusted slightly:  31 May + 1 month = 30 June.
     *
     * @param months the number of months to add (can be negative).
     * @param base   the base date.
     * @return a new date.
     */
    public static DayDate addMonths(final int months,
                                    final DayDate base) {

        final int yy = (12 * base.getYYYY() + base.getMonth().getIndex() + months - 1)
                / 12;
        final int mm = (12 * base.getYYYY() + base.getMonth().getIndex() + months - 1)
                % 12 + 1;
        final int dd = Math.min(
                base.getDayOfMonth(), DayDate.lastDayOfMonth(Month.fromInt(mm), yy)
        );
        return DayDateFactory.makeDate(dd, Month.fromInt(mm), yy);

    }

    /**
     * Creates a new date by adding the specified number of years to the base
     * date.
     *
     * @param years the number of years to add (can be negative).
     * @param base  the base date.
     * @return A new date.
     */
    public static DayDate addYears(final int years, final DayDate base) {

        final int baseY = base.getYYYY();
        final Month baseM = base.getMonth();
        final int baseD = base.getDayOfMonth();

        final int targetY = baseY + years;
        final int targetD = Math.min(
                baseD, DayDate.lastDayOfMonth(baseM, targetY)
        );

        return DayDateFactory.makeDate(targetD, baseM, targetY);

    }

    /**
     * Returns the latest date that falls on the specified day-of-the-week and
     * is BEFORE the base date.
     *
     * @param targetWeekday a code for the target day-of-the-week.
     * @param base          the base date.
     * @return the latest date that falls on the specified day-of-the-week and
     *         is BEFORE the base date.
     */
    public static DayDate getPreviousDayOfWeek(final Day targetWeekday,
                                               final DayDate base) {

        // find the date...
        final int adjust;
        final Day baseDOW = base.getDayOfWeek();
        if (baseDOW.toInt() > targetWeekday.toInt()) {
            adjust = Math.min(0, targetWeekday.toInt() - baseDOW.toInt());
        } else {
            adjust = -7 + Math.max(0, targetWeekday.toInt() - baseDOW.toInt());
        }

        return DayDate.addDays(adjust, base);

    }

    /**
     * Returns the earliest date that falls on the specified day-of-the-week
     * and is AFTER the base date.
     *
     * @param targetWeekday a code for the target day-of-the-week.
     * @param base          the base date.
     * @return the earliest date that falls on the specified day-of-the-week
     *         and is AFTER the base date.
     */
    public static DayDate getFollowingDayOfWeek(final Day targetWeekday,
                                                final DayDate base) {


        // find the date...
        final int adjust;
        final Day baseDOW = base.getDayOfWeek();
        if (baseDOW.toInt() >= targetWeekday.toInt()) {
            adjust = 7 + Math.min(0, targetWeekday.toInt() - baseDOW.toInt());
        } else {
            adjust = Math.max(0, targetWeekday.toInt() - baseDOW.toInt());
        }

        return DayDate.addDays(adjust, base);
    }

    /**
     * Returns the date that falls on the specified day-of-the-week and is
     * CLOSEST to the base date.
     *
     * @param targetDOW a code for the target day-of-the-week.
     * @param base      the base date.
     * @return the date that falls on the specified day-of-the-week and is
     *         CLOSEST to the base date.
     */
    public static DayDate getNearestDayOfWeek(final Day targetDOW,
                                              final DayDate base) {

        // find the date...
        int delta = targetDOW.toInt() - base.getDayOfWeek().toInt();
        int positiveDelta = delta + 7;
        int adjust = positiveDelta % 7;
        if (adjust > 3)
            adjust -= 7;

        return DayDate.addDays(adjust, base);

    }

    /**
     * Rolls the date forward to the last day of the month.
     *
     * @param base the base date.
     * @return a new serial date.
     */
    public DayDate getEndOfCurrentMonth(final DayDate base) {
        final int last = DayDate.lastDayOfMonth(
                base.getMonth(), base.getYYYY()
        );
        return DayDateFactory.makeDate(last, base.getMonth(), base.getYYYY());
    }

    /**
     * Returns a string corresponding to the week-in-the-month code.
     * <p/>
     * Need to find a better approach.
     *
     * @param count an integer code representing the week-in-the-month.
     * @return a string corresponding to the week-in-the-month code.
     */
    public static String weekInMonthToString(final int count) {

        switch (count) {
            case DayDate.FIRST_WEEK_IN_MONTH:
                return "First";
            case DayDate.SECOND_WEEK_IN_MONTH:
                return "Second";
            case DayDate.THIRD_WEEK_IN_MONTH:
                return "Third";
            case DayDate.FOURTH_WEEK_IN_MONTH:
                return "Fourth";
            case DayDate.LAST_WEEK_IN_MONTH:
                return "Last";
            default:
                throw new IllegalArgumentException(
                        "Invalid week in month code."
                );
        }

    }

    /**
     * Returns a string representing the supplied 'relative'.
     * <p/>
     * Need to find a better approach.
     *
     * @param relative a constant representing the 'relative'.
     * @return a string representing the supplied 'relative'.
     */
    public static String relativeToString(final int relative) {

        switch (relative) {
            case DayDate.PRECEDING:
                return "Preceding";
            case DayDate.NEAREST:
                return "Nearest";
            case DayDate.FOLLOWING:
                return "Following";
            default:
                throw new IllegalArgumentException(
                        "Invalid week in month code."
                );
        }

    }

    /**
     * Returns the serial number for the date, where 1 January 1900 = 2 (this
     * corresponds, almost, to the numbering system used in Microsoft Excel for
     * Windows and Lotus 1-2-3).
     *
     * @return the serial number for the date.
     */
    public abstract int toSerial();

    /**
     * Returns a java.util.Date.  Since java.util.Date has more precision than
     * DayDate, we need to define a convention for the 'time of day'.
     *
     * @return this as <code>java.util.Date</code>.
     */
    public abstract java.util.Date toDate();

    /**
     * Returns a description of the date.
     *
     * @return a description of the date.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description for the date.
     *
     * @param description the new description for the date.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Converts the date to a string.
     *
     * @return a string representation of the date.
     */
    public String toString() {
        return getDayOfMonth() + "-" + getMonth()
                + "-" + getYYYY();
    }

    /**
     * Returns the year (assume a valid range of 1900 to 9999).
     *
     * @return the year.
     */
    public abstract int getYYYY();

    /**
     * Returns the month (January = 1, February = 2, March = 3).
     *
     * @return the month of the year.
     */
    public abstract Month getMonth();

    /**
     * Returns the day of the month.
     *
     * @return the day of the month.
     */
    public abstract int getDayOfMonth();

    /**
     * Returns the day of the week.
     *
     * @return the day of the week.
     */
    public abstract Day getDayOfWeek();

    /**
     * Returns the difference (in days) between this date and the specified
     * 'other' date.
     * <p/>
     * The result is positive if this date is after the 'other' date and
     * negative if it is before the 'other' date.
     *
     * @param other the date being compared to.
     * @return the difference between this and the other date.
     */
    public abstract int compare(DayDate other);

    /**
     * Returns true if this DayDate represents the same date as the
     * specified DayDate.
     *
     * @param other the date being compared to.
     * @return <code>true</code> if this DayDate represents the same date as
     *         the specified DayDate.
     */
    public abstract boolean isOn(DayDate other);

    /**
     * Returns true if this DayDate represents an earlier date compared to
     * the specified DayDate.
     *
     * @param other The date being compared to.
     * @return <code>true</code> if this DayDate represents an earlier date
     *         compared to the specified DayDate.
     */
    public abstract boolean isBefore(DayDate other);

    /**
     * Returns true if this DayDate represents the same date as the
     * specified DayDate.
     *
     * @param other the date being compared to.
     * @return <code>true<code> if this DayDate represents the same date
     *         as the specified DayDate.
     */
    public abstract boolean isOnOrBefore(DayDate other);

    /**
     * Returns true if this DayDate represents the same date as the
     * specified DayDate.
     *
     * @param other the date being compared to.
     * @return <code>true</code> if this DayDate represents the same date
     *         as the specified DayDate.
     */
    public abstract boolean isAfter(DayDate other);

    /**
     * Returns true if this DayDate represents the same date as the
     * specified DayDate.
     *
     * @param other the date being compared to.
     * @return <code>true</code> if this DayDate represents the same date
     *         as the specified DayDate.
     */
    public abstract boolean isOnOrAfter(DayDate other);

    /**
     * Returns <code>true</code> if this {@link DayDate} is within the
     * specified range (INCLUSIVE).  The date order of d1 and d2 is not
     * important.
     *
     * @param d1 a boundary date for the range.
     * @param d2 the other boundary date for the range.
     * @return A boolean.
     */
    public abstract boolean isInRange(DayDate d1, DayDate d2);

    /**
     * Returns <code>true</code> if this {@link DayDate} is within the
     * specified range (caller specifies whether or not the end-points are
     * included).  The date order of d1 and d2 is not important.
     *
     * @param d1      a boundary date for the range.
     * @param d2      the other boundary date for the range.
     * @param include a code that controls whether or not the start and end
     *                dates are included in the range.
     * @return A boolean.
     */
    public abstract boolean isInRange(DayDate d1, DayDate d2,
                                      int include);

    /**
     * Returns the latest date that falls on the specified day-of-the-week and
     * is BEFORE this date.
     *
     * @param targetDOW a code for the target day-of-the-week.
     * @return the latest date that falls on the specified day-of-the-week and
     *         is BEFORE this date.
     */
    public DayDate getPreviousDayOfWeek(final Day targetDOW) {
        return getPreviousDayOfWeek(targetDOW, this);
    }

    /**
     * Returns the earliest date that falls on the specified day-of-the-week
     * and is AFTER this date.
     *
     * @param targetDOW a code for the target day-of-the-week.
     * @return the earliest date that falls on the specified day-of-the-week
     *         and is AFTER this date.
     */
    public DayDate getFollowingDayOfWeek(final Day targetDOW) {
        return getFollowingDayOfWeek(targetDOW, this);
    }

    /**
     * Returns the nearest date that falls on the specified day-of-the-week.
     *
     * @param targetDOW a code for the target day-of-the-week.
     * @return the nearest date that falls on the specified day-of-the-week.
     */
    public DayDate getNearestDayOfWeek(final Day targetDOW) {
        return getNearestDayOfWeek(targetDOW, this);
    }

}
