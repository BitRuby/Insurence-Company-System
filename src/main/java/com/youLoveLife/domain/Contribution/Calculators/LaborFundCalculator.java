package com.youLoveLife.domain.Contribution.Calculators;

import com.youLoveLife.domain.Contribution.Job;

import java.util.*;

public class LaborFundCalculator {
    private List<Job> jobsList;
    private Date from;

    private final double FIRST_3 = 583, FIRST_OVER = 468,
                         SECOND_3 = 717, SECOND_OVER = 573,
                         THIRD_3 = 852, THIRD_OVER = 679;

    public LaborFundCalculator(List<Job> jobsList, Date from) {
        this.jobsList = jobsList;
        this.from = from;
    }

    public double calculateBenefit() {
        if(!checkIfActuallyWork() && checkIfWorkedOverYear()) {
            double yearsOfWork = numberMonthsOfWork() / 12;

            if(from == null) {
                if(yearsOfWork < 5)
                    return FIRST_3;
                if(yearsOfWork >= 5 && yearsOfWork <= 20)
                    return SECOND_3;
                if(yearsOfWork > 20)
                    return THIRD_3;
            } else {
                if (yearsOfWork < 5) {
                    if (Tools.monthsBetween(from, new Date()) <= 3)
                        return FIRST_3;
                    else
                        return FIRST_OVER;
                }
                if (yearsOfWork >= 5 && yearsOfWork <= 20) {
                    if (Tools.monthsBetween(from, new Date()) <= 3)
                        return SECOND_3;
                    else
                        return SECOND_OVER;
                }
                if (yearsOfWork > 20) {
                    if (Tools.monthsBetween(from, new Date()) <= 3)
                        return THIRD_3;
                    else
                        return THIRD_OVER;
                }
            }
        }

        return 0;
    }

    public boolean checkIfActuallyWork() {
        Iterator<Job> iterator = this.jobsList.iterator();
        Job job = null;

        while (iterator.hasNext())
            job = iterator.next();

        if(job != null) {
            if (job.getToDate().after(new Date()))
                return true;
            else
                return false;
        } else
            return false;
    }

    public boolean checkIfWorkedOverYear() {
        double numberMonthsOfWork = numberMonthsOfWork();

        if(numberMonthsOfWork >= 12)
            return true;
        else
            return false;
    }

    private int numberMonthsOfWork() {
        Iterator<Job> iterator = this.jobsList.iterator();
        int numberMonthsOfWork = 0;

        while (iterator.hasNext()) {
            Job temp = iterator.next();
            Date from = temp.getFromDate();
            Date to = temp.getToDate();
            if(to.after(new Date()))
                numberMonthsOfWork += Tools.monthsBetween(from, new Date());
            else
                numberMonthsOfWork += Tools.monthsBetween(from, to);
        }
        return numberMonthsOfWork;
    }
}
