package com.test.prices.acceptance;

import com.test.prices.core.action.GetPriceAction;
import com.test.prices.core.domain.PricesRepository;
import com.test.prices.http.handler.GetPricesHandler;
import com.test.prices.http.response.PriceResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class GetPriceAcceptanceTest {

    private String dateString;
    private static final Long PRODUCT_ID = 35455L;
    private static final Long BRAND_ID = 1L;

    @Autowired
    PricesRepository repository;
    @Autowired
    GetPriceAction action;
    @Autowired
    GetPricesHandler handler;

    @BeforeEach
    public void setUp() {
        handler = new GetPricesHandler(action);
    }

    /**
     *
     * - Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void givenATest1DateWhenInvokeEndPointShouldReturnOk() throws Throwable {
        dateString = "2020-06-14-10.00.00";

        ResponseEntity<PriceResponse> response = handler.price(BRAND_ID, PRODUCT_ID, dateString);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1, response.getBody().getPriceList());
        Assertions.assertEquals(1, response.getBody().getBrandId());
        Assertions.assertEquals(35455, response.getBody().getProductId());
        Assertions.assertEquals(35.50, response.getBody().getPrice().doubleValue());
    }

    /**
     * - Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void givenATest2DateWhenInvokeEndPointShouldReturnOk() throws Throwable {
        dateString = "2020-06-14-16.00.00";

        ResponseEntity<PriceResponse> response = handler.price(BRAND_ID, PRODUCT_ID, dateString);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(2, response.getBody().getPriceList());
        Assertions.assertEquals(1, response.getBody().getBrandId());
        Assertions.assertEquals(35455, response.getBody().getProductId());
        Assertions.assertEquals(25.45, response.getBody().getPrice().doubleValue());
    }

    /**
     * - Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
     */

    @Test
    public void givenATest3DateWhenInvokeEndPointShouldReturnOk() throws Throwable {
        dateString = "2020-06-14-21.00.00";

        ResponseEntity<PriceResponse> response = handler.price(BRAND_ID, PRODUCT_ID, dateString);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1, response.getBody().getPriceList());
        Assertions.assertEquals(1, response.getBody().getBrandId());
        Assertions.assertEquals(35455, response.getBody().getProductId());
        Assertions.assertEquals(35.50, response.getBody().getPrice().doubleValue());
    }

    /**
     * - Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void givenATest4DateWhenInvokeEndPointShouldReturnOk() throws Throwable {
        dateString = "2020-06-15-10.00.00";

        ResponseEntity<PriceResponse> response = handler.price(BRAND_ID, PRODUCT_ID, dateString);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(3, response.getBody().getPriceList());
        Assertions.assertEquals(1, response.getBody().getBrandId());
        Assertions.assertEquals(35455, response.getBody().getProductId());
        Assertions.assertEquals(30.50, response.getBody().getPrice().doubleValue());
    }

    /**
     * - Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void givenATest5DateWhenInvokeEndPointShouldReturnOk() throws Throwable {
        dateString = "2020-06-16-21.00.00";

        ResponseEntity<PriceResponse> response = handler.price(BRAND_ID, PRODUCT_ID, dateString);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(4, response.getBody().getPriceList());
        Assertions.assertEquals(1, response.getBody().getBrandId());
        Assertions.assertEquals(35455, response.getBody().getProductId());
        Assertions.assertEquals(38.95, response.getBody().getPrice().doubleValue());
    }


}
