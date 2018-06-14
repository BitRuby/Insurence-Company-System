package com.youLoveLife.web;

import com.youLoveLife.domain.Contribution.LaborFundContribution;
import com.youLoveLife.repository.LaborFundContributionRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LaborFundContributionRestController {

    @Autowired
    private LaborFundContributionRepositoryImpl laborFundContributionRepository;

    @RequestMapping(value = "/getLaborFundContribution/{userID}", method = RequestMethod.GET)
    public ResponseEntity getLaborFundContribution(@PathVariable Integer userID) {
        LaborFundContribution laborFundContribution = laborFundContributionRepository.getLaborFundContribution(userID);

        if(laborFundContribution != null)
            return new ResponseEntity(laborFundContribution, HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
