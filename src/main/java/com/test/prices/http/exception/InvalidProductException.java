package com.test.prices.http.exception;

public class InvalidProductException extends Throwable {
    public InvalidProductException(int productId) {
        super(String.format("Product %s is invalid. ProductId must be greather than 0", productId));
    }
}
