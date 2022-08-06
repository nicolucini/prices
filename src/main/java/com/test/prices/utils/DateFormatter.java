package com.test.prices.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    private static final String pattern = "yyyy-MM-dd";

    public static Date toDate(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        simpleDateFormat.setLenient(false);
        return simpleDateFormat.parse(date);
    }


}
