package com.tutoriales.springbootit.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * To run the app:
 *
 * mvn spring-boot:run
 */

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.tutoriales.springbootit")
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
