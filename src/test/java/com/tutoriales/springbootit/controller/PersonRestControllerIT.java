package com.tutoriales.springbootit.controller;


import com.tutoriales.springbootit.app.Application;
import com.tutoriales.springbootit.vo.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest(randomPort = true)
public class PersonRestControllerIT {

    @Value("${local.server.port}")
    private int port;

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void shouldReturnPersonDetails() {

        final Person person = restTemplate.getForObject(getBaseUrl() + "/persona/{id}", Person.class, 1L);

        assertThat(person.getName(), is("Pepe"));
        assertThat(person.getSurname(), is("Ruiz"));
        assertThat(person.getPhoneNumber(), is("666-555-444"));

    }


    private String getBaseUrl() {
        return new StringBuilder("http://localhost:").append(port).toString();
    }
}
