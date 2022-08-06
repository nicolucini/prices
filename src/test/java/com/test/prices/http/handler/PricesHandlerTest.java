package com.test.prices.http.handler;

import com.test.prices.core.action.PricesAction;
import com.test.prices.core.domain.Price;
import com.test.prices.core.domain.PriceActionData;
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

import java.math.BigDecimal;
import java.text.ParseException;

import static org.mockito.Mockito.mock;


class PricesHandlerTest {
	private PricesHandler pricesHandler;
	@Mock private PricesAction pricesAction;

	private PriceResponse response;
	private int brandId;
	private int productId;
	private String dateString;
	private BigDecimal price;
	private int priceList;
	public static final String INVALID_DATE = "05-08-2022";
	public static final int INVALID_ID = -1;

	@BeforeEach
	private void setUp() {
		pricesAction = mock(PricesAction.class);
		pricesHandler = new PricesHandler(pricesAction);
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

	}

	@Test
	void givenAnInvalidBrandWhenGetPriceShouldReturnInvalidBrandException() {
		givenAValidData();
		brandId = INVALID_ID;

		Assertions.assertThrows(InvalidBrandException.class,
				this::whenGetPrice);
	}

	@Test
	void givenAnInvalidProductWhenGetPriceShouldReturnInvalidProductException() {
		givenAValidData();
		productId = INVALID_ID;

		Assertions.assertThrows(InvalidProductException.class,
				this::whenGetPrice);
	}


	private void givenAValidData() {
		brandId = 1;
		productId = 2;
		dateString = "2022-08-05";
	}

	private void givenAExpectedPrice() throws ParseException {
		PriceActionData actionData = new PriceActionData(brandId, productId, DateFormatter.toDate(dateString));
		price = BigDecimal.TEN;
		priceList = 3;
		Price expectedPrice = new Price(brandId, productId, priceList, price);
		Mockito.when(pricesAction.getPrice(actionData)).thenReturn(expectedPrice);
	}

	private void whenGetPrice() throws Throwable {
		response = pricesHandler.price(brandId, productId, dateString);
	}

	private void shouldReturnAValidResponse(int brandId, int productId, BigDecimal price, int priceList, PriceResponse response) {
		Assertions.assertEquals(brandId, response.getBrandId());
		Assertions.assertEquals(productId, response.getProductId());
		Assertions.assertEquals(priceList, response.getPriceList());
		Assertions.assertEquals(price, response.getPrice());
	}
}
