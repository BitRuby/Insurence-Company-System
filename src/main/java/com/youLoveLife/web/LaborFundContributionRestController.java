package com.youLoveLife.web;

import com.youLoveLife.domain.Contribution.LaborFundContribution;
import com.youLoveLife.repository.LaborFundContributionRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LaborFundContributionRestController {

    @Autowired
    private LaborFundContributionRepositoryImpl laborFundContributionRepository;

    @RequestMapping(value = "/getLaborFundContribution/{userID}", method = RequestMethod.GET)
    public LaborFundContribution getLaborFundContribution(@PathVariable Integer userID) {
        return laborFundContributionRepository.getLaborFundContribution(userID);
    }
}
