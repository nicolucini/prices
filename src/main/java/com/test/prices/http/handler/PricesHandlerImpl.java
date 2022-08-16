package com.test.prices.http.handler;

import com.test.prices.core.action.PricesAction;
import com.test.prices.core.domain.GetPriceData;
import com.test.prices.core.domain.Price;
import com.test.prices.core.domain.exception.*;
import com.test.prices.http.response.PriceResponse;
import com.test.prices.utils.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PricesHandlerImpl implements PricesHandler {

    @Autowired
    private PricesAction priceAction;

    @GetMapping("/brands/{brandId}/products/{productId}/price")
    public ResponseEntity<PriceResponse> price(@PathVariable(value = "brandId") Long brandId,
                                               @PathVariable(value = "productId") Long productId,
                                               @RequestParam(value = "date") String date) throws Exception {

        validateRequest(brandId, productId);
        Price price = priceAction.getPrice(new GetPriceData(brandId, productId, DateFormatter.toDate(date)));
        PriceResponse priceResponse = new PriceResponse(price.getBrandId(),
                price.getProductId(),
                price.getPriceList(),
                DateFormatter.fromDate(price.getStartDate()),
                DateFormatter.fromDate(price.getEndDate()),
                price.getPrice());
        return ResponseEntity.status(HttpStatus.OK).body(priceResponse);

    }

    @ExceptionHandler({InvalidDateException.class, InvalidBrandException.class, InvalidProductException.class, PriceNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleBusinessException(BusinessException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    private void validateRequest(Long brandId, Long productId) {
        if (brandId < 1) {
            throw new InvalidBrandException(brandId);
        }
        if (productId < 1) {
            throw new InvalidProductException(productId);
        }

    }

}
