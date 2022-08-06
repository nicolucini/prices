package com.test.prices.http.handler;

import com.test.prices.core.action.PricesAction;
import com.test.prices.core.domain.Price;
import com.test.prices.core.domain.PriceActionData;
import com.test.prices.http.exception.InvalidBrandException;
import com.test.prices.http.exception.InvalidDateException;
import com.test.prices.http.exception.InvalidProductException;
import com.test.prices.http.response.PriceResponse;
import com.test.prices.utils.DateFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
public class PricesHandler {
	private PricesAction pricesAction;

	public PricesHandler() {
		this.pricesAction = new PricesAction();
	}

	@GetMapping("/brands/{brandId}/products/{productId}")
	public ResponseEntity<PriceResponse> price(@PathVariable(value = "brandId") int brandId,
										@PathVariable(value = "productId") int productId,
										@RequestParam(value = "date") String date) throws Exception {

		validateRequest(brandId, productId, date);
		Price price = pricesAction.getPrice(new PriceActionData(brandId, productId, DateFormatter.toDate(date)));
		PriceResponse priceResponse = new PriceResponse(price.getBrandId(),price.getProductId(),price.getPriceList(), price.getPrice());
		return ResponseEntity.status(HttpStatus.OK).body(priceResponse);

	}

	@ExceptionHandler({ InvalidDateException.class, InvalidBrandException.class, InvalidProductException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleException(Exception exception) {
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(exception.getMessage());
	}

	private void validateRequest(int brandId, int productId, String date) throws Exception {
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
