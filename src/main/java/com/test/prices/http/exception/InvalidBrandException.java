package com.test.prices.http.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.BAD_REQUEST,
        reason = "Requested brandId is invalid"
)
public class InvalidBrandException extends RuntimeException {
    public InvalidBrandException(int brandId) {
        super(String.format("Brand %s is invalid. BrandId must be greather than 0", brandId));
    }
}
