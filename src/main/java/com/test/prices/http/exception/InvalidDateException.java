package com.test.prices.http.exception;

public class InvalidDateException extends Throwable {
    public InvalidDateException(String date) {
        super(String.format("Date %s is invalid. Date format must be yyyy-MM-dd", date));
    }
}
