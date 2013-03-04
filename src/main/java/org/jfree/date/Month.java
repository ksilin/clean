package org.jfree.date;

import java.text.DateFormatSymbols;

public enum Month {
    JANUARY(1),
    FEBRUARY(2),
    MARCH(3),
    APRIL(4),
    MAY(5),
    JUNE(6),
    JULY(7),
    AUGUST(8),
    SEPTEMBER(9),
    OCTOBER(10),
    NOVEMBER(11),
    DECEMBER(12);

    private static DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();

    Month(int index) {
        this.index = index;
    }

    public static Month fromInt(int monthIndex) {
        for (Month m : Month.values()) {
            if (m.index == monthIndex)
                return m;
        }
        throw new IllegalArgumentException("Invalid month index " + monthIndex);
    }

    private final int index;

    public int getIndex() {
        return index;
    }

    public static Month parse(String s) {
        s = s.trim();
        for (Month m : Month.values())
            if (m.matches(s))
                return m;
        try {
            return fromInt(Integer.parseInt(s));
        } catch (NumberFormatException e) {
        }
        throw new IllegalArgumentException("Invalid month " + s);
    }

    private boolean matches(String s) {
        return s.equalsIgnoreCase(toString()) ||
                s.equalsIgnoreCase(toShortString());
    }

    public String toString() {
        return dateFormatSymbols.getMonths()[index - 1];
    }

    public String toShortString() {
        return dateFormatSymbols.getShortMonths()[index - 1];
    }
}