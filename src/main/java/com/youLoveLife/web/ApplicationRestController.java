package com.youLoveLife.web;

import com.youLoveLife.domain.applications.Application;
import com.youLoveLife.repository.ApplicationRepositoryImpl;
import com.youLoveLife.repository.MessageRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ApplicationRestController {

    @Autowired
    private ApplicationRepositoryImpl applicationRepository;
    @Autowired
    private MessageRepositoryImpl messageRepository;


    @RequestMapping(value = "/sendApplication/", method = RequestMethod.POST)
    public void sendApplication(@RequestBody Application application) {
        applicationRepository.sendApplication(application);
    }

    @RequestMapping(value = "/getAllApplication", method = RequestMethod.GET)
    public ResponseEntity getAllApplication() {
        List<Application> list = applicationRepository.getAllApplication();

        if (list != null)
            return new ResponseEntity(list, HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/confirmApplication/{applicationID}", method = RequestMethod.GET)
    public void confirmApplication(@PathVariable Integer applicationID) {
        Application rentApplication = applicationRepository.confirmApplication(applicationID);
        messageRepository.sendConfirmation(rentApplication);
    }

}