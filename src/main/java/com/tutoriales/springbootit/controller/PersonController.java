package com.tutoriales.springbootit.controller;

import com.tutoriales.springbootit.exception.PersonException;
import com.tutoriales.springbootit.vo.Person;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    private List<Person> personList = new ArrayList<Person>();

    {
        personList.add(new Person(1L, "Pepe", "Ruiz", "666-555-444"));
        personList.add(new Person(2L, "Pepe", "Ruiz", "666-555-444"));
    }

    @RequestMapping(value = "/persona/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Person obtainPersonaDetails(@PathVariable("id") long id) throws PersonException {

        for(Person person : personList) {
            if(person.getId().equals(id)) {
                return person;
            }
        }

        throw new PersonException("Person has been not found!");
    }

    @ExceptionHandler(PersonException.class)
    public void personaExceptionHandler() {

        //LOG

    }
}
