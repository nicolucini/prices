package com.test.prices.core.action;

import com.test.prices.core.domain.GetPriceData;
import com.test.prices.core.domain.Price;
import com.test.prices.core.domain.PricesRepository;
import com.test.prices.core.domain.exception.PriceNotFoundException;
import com.test.prices.core.infrastructure.PriceItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Optional;

import static org.mockito.Mockito.mock;

class PricesActionTest {
    public static final Long BRAND_ID = 1L;
    public static final Long PRODUCT_ID = 2L;
    public static final Long PRICE_LIST_1 = 1L;
    private static final int PRIORITY = 1;
    private static final String CURRENCY = "EUR";
    private GetPriceAction pricesAction;
    @Mock
    private PricesRepository pricesRepository;

    private GetPriceData priceData;
    private Price actualPrice;
    private PriceItem expectedPrice;

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


    @Test
    public void givenAActionDataThatNotMatchWithAnyPriceWhenGetPriceShouldReturnPriceNotFoundException() throws Exception {
        givenAValidPriceData();
        givenAnEmptyPriceRepository();

        Assertions.assertThrows(PriceNotFoundException.class, this::whenGetPrice);
    }


    private void givenAValidPriceData() {
        priceData = new GetPriceData(BRAND_ID, PRODUCT_ID, Date.valueOf(LocalDate.of(2022,8,5)));
    }

    private void givenAPriceRepository() throws Exception {
        Calendar calendar = Calendar.getInstance();
        expectedPrice = new PriceItem(BRAND_ID, calendar.getTime(), calendar.getTime(), PRICE_LIST_1, PRODUCT_ID, PRIORITY, BigDecimal.TEN, CURRENCY);
        Mockito.when(pricesRepository.findByDate(priceData.getBrandId(), priceData.getProductId(), priceData.getDate())).thenReturn(Optional.ofNullable(expectedPrice));
    }

    private void givenAnEmptyPriceRepository() throws Exception {
        Mockito.when(pricesRepository.findByDate(priceData.getBrandId(), priceData.getProductId(), priceData.getDate())).thenReturn(Optional.empty());
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