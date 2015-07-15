package com.tutoriales.springbootit.controller;


import com.tutoriales.springbootit.app.Application;
import com.tutoriales.springbootit.vo.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest(randomPort = true)
public class PersonRestControllerIT {

    @Value("${local.server.port}")
    private int port;

    private RestTemplate restTemplate = new TestRestTemplate("user", "pass");

    @Test
    public void shouldReturnPersonDetails() {

        final ResponseEntity<Person> response = restTemplate.getForEntity(getBaseUrl() + "/person/{id}", Person.class, 1L);
        final Person person = response.getBody();

        assertThat(person.getId(), is(1L));
        assertThat(person.getName(), is("Pepe"));
        assertThat(person.getSurname(), is("Ruiz"));
        assertThat(person.getPhoneNumber(), is("666-555-444"));
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void shouldNotFindPersonDetailsWhenIdNotFound() {

        final ResponseEntity<Person> response = restTemplate.getForEntity(getBaseUrl() + "/person/{id}", Person.class, 3L);
        final Person person = response.getBody();

        assertThat(person, nullValue());
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    private String getBaseUrl() {
        return new StringBuilder("http://localhost:").append(port).toString();
    }
}
