package com.tutoriales.springbootit.controller;


import com.tutoriales.springbootit.exception.PersonException;
import com.tutoriales.springbootit.vo.Person;
import org.junit.Before;
import org.junit.Test;

import java.security.Principal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

public class PersonRestControllerTest {

    private PersonRestController personRestControllerSUT;
    private Principal principalTD;

    @Before
    public void init() {
        personRestControllerSUT = new PersonRestController();

        principalTD = mock(Principal.class);
    }

    @Test
    public void shouldReturnPersonWithId() throws PersonException {

        Person person = personRestControllerSUT.obtainPersonDetails(principalTD, 1L);

        assertThat(person.getId(), is(1L));
        assertThat(person.getName(), is("Pepe"));
        assertThat(person.getSurname(), is("Ruiz"));
        assertThat(person.getPhoneNumber(), is("666-555-444"));
    }

    @Test(expected = PersonException.class)
    public void shouldThrowPersonaExceptionWhenPersonIsNotFound() throws PersonException {

        personRestControllerSUT.obtainPersonDetails(principalTD, 3L);

        fail("Person with ID:3 should have not been found");
    }
}
