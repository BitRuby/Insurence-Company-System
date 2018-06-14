package com.youLoveLife.web;

import com.youLoveLife.domain.Contribution.HealthContribution;
import com.youLoveLife.repository.HealthContributionRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HealthContributionRestController {

    @Autowired
    private HealthContributionRepositoryImpl healthContributionRepository;

    @RequestMapping(value = "/getHealthContribution/{userID}", method = RequestMethod.GET)
    public HealthContribution getHealthContribution(@PathVariable Integer userID) {
        HealthContribution h = healthContributionRepository.getHealthContribution(userID);
        System.out.println("ID:" + h.getId() + "\namount" + h.getAmount() + "\ntoDate: " + h.getToDate() + "\nfromDate: " + h.getFromDate()
                + "\nName: " + h.getAppUser().getName() + "\nSurname: " + h.getAppUser().getSurname());
        return h;
    }

}
