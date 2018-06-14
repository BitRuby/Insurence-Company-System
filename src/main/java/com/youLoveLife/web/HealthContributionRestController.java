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
    public ResponseEntity<Map<String, Object>> getHealthContribution(@PathVariable Integer userID) {
        HealthContribution h = healthContributionRepository.getHealthContribution(userID);
        System.out.println("ID:" + h.getId() + "\namount" + h.getAmount() + "\ntoDate: " + h.getToDate() + "\nfromDate: " + h.getFromDate()
                + "\nName: " + h.getAppUser().getName() + "\nSurname: " + h.getAppUser().getSurname());




        String token = null;
        Map<String, Object> tokenMap = new HashMap<String, Object>();
        tokenMap.put("token", token);
        tokenMap.put("healthContribution", h);;
        return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.OK);

    }

}
