package com.youLoveLife.web;

import com.youLoveLife.domain.Contribution.HealthContribution;
import com.youLoveLife.repository.HealthContributionRepositoryImpl;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HealthContributionRestController {

    @Autowired
    private HealthContributionRepositoryImpl healthContributionRepository;

    @RequestMapping(value = "/getHealthContribution/{userID}", method = RequestMethod.GET)
    public ResponseEntity getHealthContribution(@PathVariable Integer userID) {
        HealthContribution healthContribution = healthContributionRepository.getHealthContribution(userID);

        if(healthContribution != null)
            return new ResponseEntity(healthContribution, HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

}
