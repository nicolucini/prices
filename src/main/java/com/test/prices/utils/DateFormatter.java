package com.test.prices.utils;

import com.test.prices.core.domain.exception.InvalidDateException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    public static final String DATE_PATTERN = "yyyy-MM-dd-HH.mm.ss";

    public static Date toDate(String date) throws InvalidDateException {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
            simpleDateFormat.setLenient(false);
            return simpleDateFormat.parse(date);
        } catch (ParseException e){
            throw new InvalidDateException(date);
        }
    }

    public static String fromDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
        return simpleDateFormat.format(date);
    }
}
