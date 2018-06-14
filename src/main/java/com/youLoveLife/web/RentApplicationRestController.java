package com.youLoveLife.web;

import com.youLoveLife.domain.applications.RentApplication;
import com.youLoveLife.repository.RentApplicationRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class RentApplicationRestController {

    @Autowired
    private RentApplicationRepositoryImpl rentApplicationRepository;

    @RequestMapping(value = "/sendRentApplication/", method = RequestMethod.POST)
    public void sendApplication(@RequestBody RentApplication rentApplication) {
        rentApplicationRepository.sendApplication(rentApplication);
    }

    @RequestMapping(value = "/getAllRentApplication", method = RequestMethod.GET)
    public ResponseEntity getAllRentApplication() {
        List<RentApplication> list = rentApplicationRepository.getAllRentApplication();

        if (list != null)
            return new ResponseEntity(list, HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    // TODO zrobic aktualizowanie z wysylaniem powiadomienia do uzytkownika o zaakceptowaniu wniosku

}
