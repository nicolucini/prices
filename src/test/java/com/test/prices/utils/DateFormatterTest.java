package com.test.prices.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

class DateFormatterTest {
    private final static String VALID_DATE = "2022-08-05-00.00.00";
    private final static String INVALID_DATE = "05-08-2022";

    @Test
    public void givenAValidDateWhenToDateShouldReturnADate() throws ParseException {
        Date date = DateFormatter.toDate(VALID_DATE);
        Assertions.assertNotNull(date);
    }

    @Test
    public void givenAInvalidDateWhenToDateShouldReturnAInvalidDateException() {
        Assertions.assertThrows(ParseException.class, () -> DateFormatter.toDate(INVALID_DATE));
    }
}