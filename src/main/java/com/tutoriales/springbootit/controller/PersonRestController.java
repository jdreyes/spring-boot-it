package com.tutoriales.springbootit.controller;

import com.tutoriales.springbootit.exception.PersonException;
import com.tutoriales.springbootit.vo.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonRestController {

    private static final Logger LOG = LoggerFactory.getLogger(PersonRestController.class);

    private List<Person> personList = new ArrayList<Person>();

    {
        personList.add(new Person(1L, "Pepe", "Ruiz", "666-555-444"));
        personList.add(new Person(2L, "Pepe", "Ruiz", "666-555-444"));
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Person obtainPersonDetails(@AuthenticationPrincipal Principal principal, @PathVariable("id") long id) throws PersonException {

        LOG.info(new StringBuilder("User: ").append(principal.getName()).append(" requesting for person: ").append(id).toString());

        for(Person person : personList) {
            if(person.getId().equals(id)) {
                return person;
            }
        }

        throw new PersonException("Person has not been found!");
    }

    @ExceptionHandler(PersonException.class)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void personExceptionHandler(final Exception exception) {
        LOG.warn("REST Service exception: " + exception.getMessage());
    }
}
