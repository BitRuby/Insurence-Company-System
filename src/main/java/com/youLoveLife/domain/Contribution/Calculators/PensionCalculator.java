package com.youLoveLife.domain.Contribution.Calculators;

import java.util.*;

public class PensionCalculator {

    private SocialContributionCalculator socialContributionCalculator;
    private Date dateOfBirth;

    public PensionCalculator(SocialContributionCalculator socialContributionCalculator, Date dateOfBirth) {
        this.socialContributionCalculator = socialContributionCalculator;
        this.dateOfBirth = dateOfBirth;
    }

    public Double calculatePension() {
        double amontContribution = socialContributionCalculator.calculateContribution();
        return amontContribution * 0.01;
    }

    public Date calculateFrom() {
        Calendar date = Calendar.getInstance();
        date.setTime(this.dateOfBirth);
        date.add(Calendar.YEAR, 60);

        return date.getTime();
    }

}
