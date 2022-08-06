package com.test.prices.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    public static final String DATE_PATTERN = "yyyy-MM-dd-HH.mm.ss";

    public static Date toDate(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
        simpleDateFormat.setLenient(false);
        return simpleDateFormat.parse(date);
    }


}
