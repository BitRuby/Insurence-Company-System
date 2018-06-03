package com.youLoveLife.domain.Contribution.Calculators;

import com.youLoveLife.domain.Contribution.Job;

import java.util.*;

public class HealthContributionCalculator {

    private Job currentJob;

    public HealthContributionCalculator(Job currentJob) {
        this.currentJob = currentJob;
    }

    public double calculateContribution() {
        if(isInsured())
            return currentJob.getSalary() * 0.86;
        else
            return 0;
    }

    public boolean isInsured() {
        if(currentJob.getToDate().after(new Date()))
            return true;
        else
            return false;
    }
}
