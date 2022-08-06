package com.test.prices.http.handler;

import com.test.prices.core.action.GetPricesAction;
import com.test.prices.core.domain.Price;
import com.test.prices.core.domain.GetPriceData;
import com.test.prices.http.exception.InvalidBrandException;
import com.test.prices.http.exception.InvalidDateException;
import com.test.prices.http.exception.InvalidProductException;
import com.test.prices.http.response.PriceResponse;
import com.test.prices.utils.DateFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;

class PricesHandlerTest {
	private PricesHandler pricesHandler;
	@Mock private GetPricesAction pricesAction;

	private ResponseEntity<PriceResponse> response;
	private int brandId;
	private int productId;
	private String dateString;
	private BigDecimal price;
	private int priceList;
	public static final String INVALID_DATE = "05-08-2022";
	public static final int INVALID_ID = -1;

	@BeforeEach
	private void setUp() {
		pricesAction = mock(GetPricesAction.class);
		pricesHandler = new PricesHandler();
	}

	@Test
	void givenABrandIdAndProductIdAndDateWhenGetPriceShouldReturnAValidResponse() throws Throwable {
		givenAValidData();
		givenAExpectedPrice();

		whenGetPrice();

		shouldReturnAValidResponse(brandId, productId, price, priceList, response);

	}

	@Test
	void givenAnInvalidDateWhenGetPriceShouldReturnInvalidDateException() {
		givenAValidData();
		dateString = INVALID_DATE;

		Assertions.assertThrows(InvalidDateException.class,
				this::whenGetPrice);

		shouldResponseBadRequest();
	}

	@Test
	void givenAnInvalidBrandWhenGetPriceShouldReturnInvalidBrandException() {
		givenAValidData();
		brandId = INVALID_ID;

		Assertions.assertThrows(InvalidBrandException.class,
				this::whenGetPrice);

		shouldResponseBadRequest();
	}

	@Test
	void givenAnInvalidProductWhenGetPriceShouldReturnInvalidProductException() {
		givenAValidData();
		productId = INVALID_ID;

		Assertions.assertThrows(InvalidProductException.class,
				this::whenGetPrice);

		shouldResponseBadRequest();
	}


	private void givenAValidData() {
		brandId = 1;
		productId = 2;
		dateString = "2022-08-05-00.00.00";
	}

	private void givenAExpectedPrice() throws Throwable {
		GetPriceData actionData = new GetPriceData(brandId, productId, DateFormatter.toDate(dateString));
		price = BigDecimal.TEN;
		priceList = 3;
		Price expectedPrice = new Price(brandId, productId, priceList, price);
		Mockito.when(pricesAction.getPrice(actionData)).thenReturn(expectedPrice);
	}

	private void whenGetPrice() throws Throwable {
		response = pricesHandler.price(brandId, productId, dateString);
	}

	private void shouldResponseBadRequest() {
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	private void shouldReturnAValidResponse(int brandId, int productId, BigDecimal price, int priceList, ResponseEntity<PriceResponse> response) {
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(brandId, response.getBody().getBrandId());
		Assertions.assertEquals(productId, response.getBody().getProductId());
		Assertions.assertEquals(priceList, response.getBody().getPriceList());
		Assertions.assertEquals(price, response.getBody().getPrice());
	}
}
