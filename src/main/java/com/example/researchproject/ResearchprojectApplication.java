package com.example.researchproject;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ResearchprojectApplication {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ResearchprojectApplication.class);
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
        SpringApplication.run(ResearchprojectApplication.class, args);

        log.info("SpringBoot setup here tester updated");
    }

}
