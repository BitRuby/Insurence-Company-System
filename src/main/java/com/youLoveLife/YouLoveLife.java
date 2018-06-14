package com.youLoveLife;

import com.youLoveLife.web.HealthContributionRestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackageClasses = HealthContributionRestController.class)
public class YouLoveLife {

    public static void main(String[] args) {
        SpringApplication.run(YouLoveLife.class, args);
    }
}
