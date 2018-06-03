package com.youLoveLife.domain.Contribution.Calculators;

import com.youLoveLife.domain.Contribution.Job;

import java.util.*;

public class SocialContributionCalculator {
    private List<Job> jobsList;

    public SocialContributionCalculator(List<Job> jobsList) {
        this.jobsList = jobsList;
    }

    public double calculateContribution() {
        return getTotalRemuneration();
    }

    private double getTotalRemuneration() {
        Iterator<Job> iterator = jobsList.iterator();
        double totalSalary = 0;
        double contribution = 0;
        int numberMonthsOfWork = 0;

        while (iterator.hasNext()) {
            Job temp = iterator.next();
            Date from = temp.getFromDate();
            Date to = temp.getToDate();
            Double salary = temp.getSalary();
            numberMonthsOfWork = Tools.monthsBetween(from, to);
            totalSalary += salary * numberMonthsOfWork;
            contribution += (totalSalary * 0.17) + (totalSalary * 0.11);
        }
        return contribution;
    }



}
