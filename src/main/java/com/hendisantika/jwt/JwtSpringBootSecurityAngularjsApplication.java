package com.hendisantika.jwt;

import com.hendisantika.jwt.domain.AppUser;
import com.hendisantika.jwt.repository.AppUserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JwtSpringBootSecurityAngularjsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtSpringBootSecurityAngularjsApplication.class, args);
    }
}
