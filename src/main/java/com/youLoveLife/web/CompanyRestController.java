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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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


    @RequestMapping(value = "/getAllCompanies")
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
    public void addNewEmployee(@RequestBody AppUser appUser, @RequestBody Integer companyID, @RequestBody Job job) {
        companyRepository.addNewEmployee(appUser, companyID);

        Company company = companyRepository.getCompanyByID(companyID);

        appUserRepository.addNewJob(appUser, company, job);
        messageRepository.sendNewJobInfo(appUser, companyID, job);
    }

    @RequestMapping(value = "/removeEmployee/{userID}/{companyID}", method = RequestMethod.GET)
    public void removeEmployee(@PathVariable Integer userID, @PathVariable Integer companyID) {
        appUserRepository.terminateContract(Long.valueOf(userID));
        companyRepository.deleteEmployee(Long.valueOf(userID), Long.valueOf(companyID));
        messageRepository.sendTerminateContractMessage(userID, companyID);
    }

    @RequestMapping(value = "/unregisterCompany/{companyID}", method = RequestMethod.GET)
    public void unregisterCompany(Integer companyID) {


    }
}
