package com.test.prices.http.handler;

import com.test.prices.core.action.PricesAction;
import com.test.prices.core.domain.Price;
import com.test.prices.core.domain.PriceActionData;
import com.test.prices.http.exception.InvalidBrandException;
import com.test.prices.http.exception.InvalidDateException;
import com.test.prices.http.exception.InvalidProductException;
import com.test.prices.http.response.PriceResponse;
import com.test.prices.utils.DateFormatter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.ParseException;

@RestController
public class PricesHandler {
	private PricesAction pricesAction;

	public PricesHandler(PricesAction pricesAction) {
		this.pricesAction = pricesAction;
	}

	@GetMapping("/price")
	public PriceResponse price(@RequestParam(value = "brandId") int brandId,
							   @RequestParam(value = "productId") int productId,
							   @RequestParam(value = "date") String date) throws Throwable {
		validateRequest(brandId, productId, date);
		Price price = pricesAction.getPrice(new PriceActionData(brandId, productId, DateFormatter.toDate(date)));
		return new PriceResponse(price.getBrandId(),price.getProductId(),price.getPriceList(), price.getPrice());
	}

	private void validateRequest(int brandId, int productId, String date) throws Throwable {
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
