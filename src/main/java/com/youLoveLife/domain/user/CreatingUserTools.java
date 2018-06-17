package com.youLoveLife.domain.user;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CreatingUserTools {

    public static Date setDate(int year, int month, int day) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.set(year, month, day);
        return cal.getTime();
    }

}
