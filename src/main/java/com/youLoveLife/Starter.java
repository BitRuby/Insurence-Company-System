package com.youLoveLife;

import com.youLoveLife.domain.Problem;
import com.youLoveLife.domain.user.AppUser;
import com.youLoveLife.repository.*;
import com.youLoveLife.web.AppUserRestController;
import com.youLoveLife.web.ProblemRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Component
@Scope("singleton")
public class Starter implements CommandLineRunner {

    @Autowired
    private AppUserRepositoryImpl repo;
    @Autowired
    private MessageRepositoryImpl repository;

    @Autowired
    private AppUserRestController rest;

    @Autowired
    private ApplicationRepositoryImpl applicationRepository;
    @Autowired
    private SocialContributionRepositoryImpl contributionRepository;
    @Autowired
    private AppUserRepository appUserRepository;/*
    @Autowired
    private CompanyRepositoryImpl companyRepository;
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private ProblemRepositoryImpl problemRepositoryImpl;
    @Autowired
    private ProblemRestController p;
    */

    @Override
    @Transactional
    public void run(String... strings) throws Exception {
        repo.setFirstUsers();
        //repository.sendEveryone("Bicz", "Biczes");
        repository.sendWelcomeMessage();
        //AppUser u = appUserRepository.findOne(Long.valueOf(1824));

        //ResponseEntity a = rest.userById(Long.valueOf(1824));
        //AppUser u = (AppUser) a.getBody();

        //System.out.println("\n\n\n\n\n\n\n\n*******************************************\n\n" + u + "\n\n\n\n\n\n\n*************************");

        /*
        List<String> role2 = new ArrayList<>();
        role2.add(new String("USER"));

        Address address = new Address("Zadupie", "23", "Źródłowa", "25-444 Zadupie", "Polska");
        Date dateOfBirth = CreatingUserTools.setDate(1980,4,22);

        AppUser appUser = new AppUser("Zbyszek", "Bicz", address, dateOfBirth, "zbyszek", "zbyszek", role2, null);

        em.persist(appUser);
        */

        /*
        Company company = companyRepository.getCompanyByID(1510);

        List<AppUser> list = repo.getUsersList();
        Iterator<AppUser> iterator = list.iterator();
        AppUser appUser = null;

        while (iterator.hasNext()) {
            appUser = iterator.next();

            if(appUser.getId().equals(Long.valueOf(1541)))
                break;
        }


        Job job = new Job("Manager w " + company.getCompanyName(), CreatingUserTools.setDate(2018,05,17), CreatingUserTools.setDate(2021,5,16), Double.valueOf(2300), appUser);

        companyRepository.addNewEmployee(appUser, 1510);

        repo.addNewJob(appUser, company, job);
        repository.sendNewJobInfo(appUser, 1510, job);
        repo.updateUser(1541);
        */


        //problemRepositoryImpl.readProblem(Long.valueOf(1621));
        //p.deleteProblem(Long.valueOf(1621));

        /*
        repo.terminateContract(Long.valueOf(1541));
        companyRepository.deleteEmployee(Long.valueOf(1541), Long.valueOf(1510));
        repo.updateUser(1541);
        repository.sendTerminateContractMessage(1541,1510);
        */


    }

    /* TODO
        dropy do bazy

        drop table app_user cascade constraint;
drop table app_user_roles cascade constraint;
drop table health_contribution cascade constraint;
drop table job cascade constraint;
drop table labor_fund_contribution cascade constraint;
drop table message cascade constraint;
drop table pension cascade constraint;
drop table rent cascade constraint;
drop table social_contribution cascade constraint;
drop table application cascade constraint;
drop table company cascade constraint;
drop table problem cascade constraint;

     */
}
