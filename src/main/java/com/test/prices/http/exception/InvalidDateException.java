package com.test.prices.http.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.BAD_REQUEST,
        reason = "Requested date is invalid"
)
public class InvalidDateException extends Exception {
    public InvalidDateException(String date) {
        super(String.format("Date %s is invalid. Date format must be yyyy-MM-dd", date));
    }
}
