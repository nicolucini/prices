package com.test.prices.http.handler;

import com.test.prices.core.action.PricesAction;
import com.test.prices.core.domain.GetPriceData;
import com.test.prices.core.domain.Price;
import com.test.prices.core.domain.exception.InvalidBrandException;
import com.test.prices.core.domain.exception.InvalidDateException;
import com.test.prices.core.domain.exception.InvalidProductException;
import com.test.prices.http.response.PriceResponse;
import com.test.prices.utils.DateFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.*;

@SpringBootTest
class PricesHandlerTest {
	public static final long BRAND_ID = 1L;
	public static final long PRODUCT_ID = 2L;
	public static final String DATE_STRING = "2022-08-05-00.00.00";
	public static final Long PRICE_LIST = 3L;
	private static final int PRIORITY = 1;
	private static final String CURRENCY = "EUR";

	@Mock
	private PricesAction pricesAction;
	@InjectMocks
	private PricesHandler pricesHandler = new PricesHandlerImpl();

	private ResponseEntity<PriceResponse> response;
	private Long brandId;
	private Long productId;
	private String dateString;
	private BigDecimal price;
	private Date startDate;
	private Date endDate;
	private Long priceList;
	public static final String INVALID_DATE = "05-08-2022";
	public static final Long INVALID_ID = -1L;


	@Test
	void givenABrandIdAndProductIdAndDateWhenGetPriceShouldReturnAValidResponse() throws Throwable {
		givenAValidData();
		givenAExpectedPrice();

		whenGetPrice();

		shouldReturnAValidResponse();

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
		brandId = BRAND_ID;
		productId = PRODUCT_ID;
		dateString = DATE_STRING;
	}

	private void givenAExpectedPrice() throws Throwable {
		GetPriceData actionData = new GetPriceData(brandId, productId, DateFormatter.toDate(dateString));
		price = BigDecimal.TEN;
		priceList = PRICE_LIST;
		startDate = Calendar.getInstance().getTime();
		endDate = Calendar.getInstance().getTime();
		Price expectedPrice = new Price(brandId, startDate, endDate, PRICE_LIST, productId, PRIORITY, price, CURRENCY);
		when(pricesAction.getPrice(actionData)).thenReturn(expectedPrice);
	}

	private void whenGetPrice() throws Throwable {
		response = pricesHandler.price(brandId, productId, dateString);
	}

	private void shouldReturnAValidResponse() {
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(brandId, response.getBody().getBrandId());
		Assertions.assertEquals(productId, response.getBody().getProductId());
		Assertions.assertEquals(priceList, response.getBody().getPriceList());
		Assertions.assertEquals(price, response.getBody().getPrice());
		Assertions.assertNotNull(response.getBody().getStartDate());
		Assertions.assertNotNull(response.getBody().getEndDate());
	}
}
