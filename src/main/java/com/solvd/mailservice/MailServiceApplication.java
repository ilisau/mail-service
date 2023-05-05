package com.solvd.mailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MailServiceApplication {

    /**
     * Main method.
     *
     * @param args arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(MailServiceApplication.class, args);
    }

}
