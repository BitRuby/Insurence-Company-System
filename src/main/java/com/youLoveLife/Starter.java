package com.youLoveLife;

import com.youLoveLife.domain.Contribution.Rent;
import com.youLoveLife.domain.Contribution.SocialContribution;
import com.youLoveLife.domain.Message;
import com.youLoveLife.domain.applications.RentApplication;
import com.youLoveLife.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
@Scope("singleton")
public class Starter implements CommandLineRunner {

    @Autowired
    private AppUserRepositoryImpl repo;
    @Autowired
    private MessageRepositoryImpl repository;
    @Autowired
    private RentApplicationRepositoryImpl applicationRepository;
    @Autowired
    private SocialContributionRepositoryImpl contributionRepository;

    @Override
    @Transactional
    public void run(String... strings) throws Exception {
        repo.setFirstUsers();
        repository.sendEveryone("Bicz", "Biczes");

        //RentApplication rentApplication = new RentApplication("Tadeusz", "Kozieł", "Pińczów", "22", "Grodziskowa", "28-400", "Polska", false, java.lang.Long.valueOf(905));
        //applicationRepository.sendApplication(rentApplication);


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
drop table rent_application cascade constraint;

     */
}
