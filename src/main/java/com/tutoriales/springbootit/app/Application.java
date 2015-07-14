package com.tutoriales.springbootit.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.annotation.Resource;

/**
 * To run the app:
 *
 * mvn spring-boot:run
 */

@SpringBootApplication
@EnableAutoConfiguration
@EnableWebSecurity
@ComponentScan("com.tutoriales.springbootit")
public class Application extends WebSecurityConfigurerAdapter {

    @Resource
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("pass").roles("USER");
    }

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
