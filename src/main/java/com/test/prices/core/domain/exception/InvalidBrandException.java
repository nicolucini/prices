package com.test.prices.core.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.BAD_REQUEST,
        reason = "Requested brandId is invalid"
)
public class InvalidBrandException extends BusinessException {
    public InvalidBrandException(Long brandId) {
        super(String.format("Brand %s is invalid. BrandId must be greater than 0", brandId));
    }
}
