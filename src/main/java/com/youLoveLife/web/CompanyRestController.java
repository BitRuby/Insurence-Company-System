package com.youLoveLife.web;

import com.youLoveLife.domain.Company;
import com.youLoveLife.domain.Contribution.Job;
import com.youLoveLife.domain.user.AppUser;
import com.youLoveLife.repository.AppUserRepositoryImpl;
import com.youLoveLife.repository.CompanyRepositoryImpl;
import com.youLoveLife.repository.MessageRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@Controller
public class CompanyRestController {

    @Autowired
    private CompanyRepositoryImpl companyRepository;
    @Autowired
    private AppUserRepositoryImpl appUserRepository;
    @Autowired
    private MessageRepositoryImpl messageRepository;


    @RequestMapping(value = "/getAllCompanies", method = RequestMethod.GET)
    public ResponseEntity getAllCompanies() {
        List<Company> list = companyRepository.getCompanyList();

        if(list != null)
            return new ResponseEntity(list, HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/createCompany/", method = RequestMethod.POST)
    public void createCompany(@RequestBody Company company) {
        companyRepository.createCompany(company);
        appUserRepository.setUserOwnCopany(company);
    }

    @RequestMapping(value = "/addNewEmployee/", method = RequestMethod.POST)
    public ResponseEntity addNewEmployee(@RequestParam AppUser appUser, @RequestParam Integer companyID, @RequestParam Job job) {
        try {
            companyRepository.addNewEmployee(appUser, companyID);

            Company company = companyRepository.getCompanyByID(companyID);

            appUserRepository.addNewJob(appUser, company, job);
            messageRepository.sendNewJobInfo(appUser, companyID, job);

            return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/removeEmployee/{userID}/{companyID}", method = RequestMethod.GET)
    public ResponseEntity removeEmployee(@PathVariable Integer userID, @PathVariable Integer companyID) {
        try {
            appUserRepository.terminateContract(Long.valueOf(userID));
            companyRepository.deleteEmployee(Long.valueOf(userID), Long.valueOf(companyID));
            messageRepository.sendTerminateContractMessage(userID, companyID);

            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/unregisterCompany/{companyID}", method = RequestMethod.GET)
    public ResponseEntity unregisterCompany(Integer companyID) {
        try {
            companyRepository.unregisterCompany(companyID);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
