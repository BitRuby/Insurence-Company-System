package com.youLoveLife;

import com.youLoveLife.repository.AppUserRepositoryImpl;
import com.youLoveLife.repository.MessageRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class Starter implements CommandLineRunner {

    @Autowired
    AppUserRepositoryImpl repo;
    @Autowired
    MessageRepositoryImpl repository;

    @Override
    public void run(String... strings) throws Exception {
        repo.setFirstUsers();
        repository.sendEveryone("Bicz", "Biczes");
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

     */
}
