package com.boostrap.landingpage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.security.Security;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class LandingPageApplication {

    public static void main(String[] args) {
        SpringApplication.run(LandingPageApplication.class, args);
    }

}
