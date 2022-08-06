package com.test.prices.http.exception;

public class InvalidBrandException extends Throwable {
    public InvalidBrandException(int brandId) {
        super(String.format("Brand %s is invalid. BrandId must be greather than 0", brandId));
    }
}
