package com.youLoveLife.web;

import com.youLoveLife.domain.Contribution.SocialContribution;
import com.youLoveLife.repository.SocialContributionRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SocialContributionRestController {

    @Autowired
    private SocialContributionRepositoryImpl socialContributionRepository;

    @RequestMapping(value = "/getSocialContribution/{userID}", method = RequestMethod.GET)
    public SocialContribution getSocialContribution(@PathVariable Integer userID) {
        return socialContributionRepository.getSocialContribution(userID);
    }

}
