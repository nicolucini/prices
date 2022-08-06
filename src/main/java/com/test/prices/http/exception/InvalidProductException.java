package com.test.prices.http.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.BAD_REQUEST,
        reason = "Requested productId is invalid"
)
public class InvalidProductException extends Exception {
    public InvalidProductException(Long productId) {
        super(String.format("Product %s is invalid. ProductId must be greather than 0", productId));
    }
}
