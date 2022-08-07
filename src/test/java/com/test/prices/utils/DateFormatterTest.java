package com.test.prices.utils;

import com.test.prices.core.domain.exception.InvalidDateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

class DateFormatterTest {
    private final static String VALID_DATE = "2022-08-05-00.00.00";
    private final static String INVALID_DATE = "05-08-2022";

    @Test
    public void givenAValidDateWhenToDateShouldReturnADate() {
        Date date = DateFormatter.toDate(VALID_DATE);
        Assertions.assertNotNull(date);
    }

    @Test
    public void givenAInvalidDateWhenToDateShouldReturnAInvalidDateException() {
        Assertions.assertThrows(InvalidDateException.class, () -> DateFormatter.toDate(INVALID_DATE));
    }

    @Test
    public void givenAValidDateWhenFromDateShouldReturnADate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022,Calendar.AUGUST,7, 0,0,0);
        String dateString = DateFormatter.fromDate(calendar.getTime());
        Assertions.assertEquals("2022-08-07-00.00.00", dateString);
    }

}