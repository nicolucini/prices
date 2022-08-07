package com.test.prices.core.action;

import com.test.prices.core.domain.GetPriceData;
import com.test.prices.core.domain.GetPriceResponseData;
import com.test.prices.core.domain.PricesRepository;
import com.test.prices.core.domain.exception.PriceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import static org.mockito.Mockito.mock;

class PricesActionTest {
    public static final long BRAND_ID = 1L;
    public static final long PRODUCT_ID = 2L;
    public static final long PRICE_LIST_1 = 1L;
    private GetPriceAction pricesAction;
    @Mock
    private PricesRepository pricesRepository;

    private GetPriceData priceData;
    private GetPriceResponseData actualPrice;
    private GetPriceResponseData expectedPrice;

    @BeforeEach
    void setUp() {
        pricesRepository = mock(PricesRepository.class);
        pricesAction = new GetPriceAction(pricesRepository);
    }

    @Test
    public void givenAActionDataWhenGetPriceShouldReturnAPrice() throws Throwable {
        givenAValidPriceData();
        givenAPriceRepository();

        whenGetPrice();

        shouldReturnAPrice();
    }


    private void givenAValidPriceData() {
        priceData = new GetPriceData(BRAND_ID, PRODUCT_ID, Date.valueOf(LocalDate.of(2022,8,5)));
    }

    private void givenAPriceRepository() throws PriceNotFoundException {
        expectedPrice = new GetPriceResponseData(BRAND_ID, PRODUCT_ID, PRICE_LIST_1, BigDecimal.TEN);
        Mockito.when(pricesRepository.findByDate(priceData.getBrandId(), priceData.getProductId(), priceData.getDate())).thenReturn(expectedPrice);
    }

    private void whenGetPrice() throws Throwable {
        actualPrice = pricesAction.getPrice(priceData);
    }

    private void shouldReturnAPrice() {
        Assertions.assertEquals(expectedPrice.getPrice(), actualPrice.getPrice());
        Assertions.assertEquals(expectedPrice.getPriceList(), actualPrice.getPriceList());
        Assertions.assertEquals(expectedPrice.getBrandId(), actualPrice.getBrandId());
        Assertions.assertEquals(expectedPrice.getProductId(), actualPrice.getProductId());
    }

}