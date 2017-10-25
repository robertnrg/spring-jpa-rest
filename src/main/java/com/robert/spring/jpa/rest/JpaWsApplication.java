package com.robert.spring.jpa.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Roberto Noriega
 * @version 1.0.0
 * @since 20/10/17
 **/
@SpringBootApplication
@EnableJpaRepositories("com.robert.spring.jpa.rest.repository")
public class JpaWsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaWsApplication.class, args);
    }
}
