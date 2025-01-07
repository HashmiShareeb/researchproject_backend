package com.example.researchproject;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResearchprojectApplication {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ResearchprojectApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ResearchprojectApplication.class, args);

        log.info("SpringBoot setup here tester updated");
    }

}
