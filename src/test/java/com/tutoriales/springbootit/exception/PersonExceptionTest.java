package com.tutoriales.springbootit.exception;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonExceptionTest {

    private PersonException personExceptionSUT = new PersonException();

    @Test
    public void shouldExtendException() {
        assertThat(personExceptionSUT instanceof Exception, is(true));
    }

    @Test
    public void shouldHaveConstructorWithParams() {

        personExceptionSUT = new PersonException("Message");

        assertThat(personExceptionSUT.getMessage(), notNullValue());
    }
}
