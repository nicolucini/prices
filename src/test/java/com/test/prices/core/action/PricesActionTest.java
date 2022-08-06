package com.test.prices.core.action;

import com.test.prices.core.domain.GetPriceData;
import com.test.prices.core.domain.Price;
import com.test.prices.core.domain.PricesRepository;
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
    private GetPricesAction pricesAction;
    @Mock
    private PricesRepository pricesRepository;

    private GetPriceData priceData;
    private Price actualPrice;
    private Price expectedPrice;

    @BeforeEach
    void setUp() {
        pricesRepository = mock(PricesRepository.class);
        pricesAction = new GetPricesAction(pricesRepository);
    }

    @Test
    public void givenAActionDataWhenGetPriceShouldReturnAPrice() throws Throwable {
        givenAValidPriceData();
        givenAPriceRepository();

        whenGetPrice();

        shouldReturnAPrice();
    }

    private void givenAValidPriceData() {
        priceData = new GetPriceData(1,2, Date.valueOf(LocalDate.of(2022,8,5)));
    }

    private void givenAPriceRepository() throws Throwable {
        expectedPrice = new Price(1,1,1, BigDecimal.TEN);
        Mockito.when(pricesRepository.getPrice(priceData)).thenReturn(expectedPrice);
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