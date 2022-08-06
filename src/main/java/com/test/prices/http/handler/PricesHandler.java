package com.test.prices.http.handler;

import com.test.prices.core.action.GetPriceAction;
import com.test.prices.core.domain.GetPriceResponseData;
import com.test.prices.core.domain.GetPriceData;
import com.test.prices.core.domain.exception.PriceNotFoundException;
import com.test.prices.http.exception.InvalidBrandException;
import com.test.prices.http.exception.InvalidDateException;
import com.test.prices.http.exception.InvalidProductException;
import com.test.prices.http.response.PriceResponse;
import com.test.prices.utils.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
public class PricesHandler {
	@Autowired
	private final GetPriceAction getPricesAction;

	public PricesHandler(GetPriceAction getPricesAction) {
		this.getPricesAction = getPricesAction;
	}

	@GetMapping("/brands/{brandId}/products/{productId}/price")
	public ResponseEntity<PriceResponse> price(@PathVariable(value = "brandId") Long brandId,
										@PathVariable(value = "productId") Long productId,
										@RequestParam(value = "date") String date) throws Throwable {

		validateRequest(brandId, productId, date);
		GetPriceResponseData price = getPricesAction.getPrice(new GetPriceData(brandId, productId, DateFormatter.toDate(date)));
		PriceResponse priceResponse = new PriceResponse(price.getBrandId(),price.getProductId(),price.getPriceList(), price.getPrice());
		return ResponseEntity.status(HttpStatus.OK).body(priceResponse);

	}

	@ExceptionHandler({ InvalidDateException.class, InvalidBrandException.class, InvalidProductException.class, PriceNotFoundException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleException(Throwable exception) {
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(exception.getMessage());
	}

	private void validateRequest(Long brandId, Long productId, String date) throws Exception {
		try {
			DateFormatter.toDate(date);
		} catch (ParseException e){
			throw new InvalidDateException(date);
		}
		if(brandId < 1) {
			throw new InvalidBrandException(brandId);
		}
		if(productId < 1) {
			throw new InvalidProductException(productId);
		}

	}

}
