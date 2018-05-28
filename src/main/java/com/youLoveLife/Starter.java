package com.youLoveLife;

import com.youLoveLife.repository.AppUserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class Starter implements CommandLineRunner {

    @Autowired
    AppUserRepositoryImpl repo;

    @Override
    public void run(String... strings) throws Exception {
        repo.setFirstUsers();
    }
}
