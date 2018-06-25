package com.youLoveLife.web;

import com.youLoveLife.domain.applications.Application;
import com.youLoveLife.enums.ApplicationType;
import com.youLoveLife.repository.ApplicationRepositoryImpl;
import com.youLoveLife.repository.MessageRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ApplicationRestController {

    @Autowired
    private ApplicationRepositoryImpl applicationRepository;
    @Autowired
    private MessageRepositoryImpl messageRepository;


    @RequestMapping(value = "/sendApplication", method = RequestMethod.POST)
    public ResponseEntity sendApplication(@RequestParam String building, @RequestParam String city, @RequestParam String country,
                                          @RequestParam String name, @RequestParam String postcode, @RequestParam String street,
                                          @RequestParam String surname, @RequestParam ApplicationType type, @RequestParam Integer userID) {

        //ApplicationType type, String name,  String surname, String city, String building, String street, String postcode, String country, boolean isApproved, Long userID
        Application application = new Application(type, name, surname, city, building, street, postcode, country, false, Long.valueOf(userID));

        try {
            applicationRepository.sendApplication(application);
            System.out.println("\n\n\n*************************************\n DODAWANIE");
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    public ResponseEntity confirmApplication(@PathVariable Integer applicationID) {
        try {
            Application application = applicationRepository.confirmApplication(applicationID);
            messageRepository.sendConfirmation(application);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
