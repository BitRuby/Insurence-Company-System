package com.youLoveLife.domain.Contribution.Calculators;

import java.util.Calendar;
import java.util.Date;

public class Tools {

    public static int monthsBetween(Date from, Date to) {
        Calendar fromC = Calendar.getInstance();
        fromC.setTime(from);
        Calendar toC = Calendar.getInstance();
        toC.setTime(to);
        int yearsInBetween = toC.get(Calendar.YEAR) - fromC.get(Calendar.YEAR);
        int monthsDiff = toC.get(Calendar.MONTH) - fromC.get(Calendar.MONTH);

        int months = yearsInBetween * 12 + monthsDiff;

        return months;
    }
}
