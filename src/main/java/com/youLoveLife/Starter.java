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

    @Override
    @Transactional
    public void run(String... strings) throws Exception {
        repository.sendWelcomeMessage();
        //repo.updateContributions();
        //repo.setFirstUsers();
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


email: xsavir-buyer-1@wp.pl
     */
}
