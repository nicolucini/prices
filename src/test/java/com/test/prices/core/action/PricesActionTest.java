package com.test.prices.core.action;

import com.test.prices.core.domain.GetPriceData;
import com.test.prices.core.domain.GetPriceResponseData;
import com.test.prices.core.domain.PricesRepository;
import com.test.prices.core.domain.exception.PriceNotFoundException;
import com.test.prices.core.infrastructure.JPAPricesRepositoryImpl;
import com.test.prices.core.infrastructure.PriceItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.mockito.Mockito.mock;

class PricesActionTest {
    public static final long BRAND_ID = 1L;
    public static final long PRODUCT_ID = 2L;
    public static final int PRIORITY = 1;
    public static final long PRICE_LIST_1 = 1L;
    public static final long PRICE_LIST_2 = 2L;
    public static final String CURRENCY = "EUR";
    private GetPriceAction pricesAction;
    @Mock
    private PricesRepository pricesRepository;

    private GetPriceData priceData;
    private GetPriceResponseData actualPrice;
    private List<PriceItem> expectedPrices;

    @BeforeEach
    void setUp() {
        pricesRepository = mock(JPAPricesRepositoryImpl.class);
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
    public void givenAActionDataBetweenTwoPricesWhenGetPriceShouldReturnFirstResult() throws Throwable {
        givenAValidPriceData();
        givenAPriceRepositoryWith2Results();

        whenGetPrice();

        shouldReturnAPrice();
    }

    @Test
    public void givenAActionDataThatNotMatchWithAnyPriceWhenGetPriceShouldReturnPriceNotFoundException() {
        givenAValidPriceData();
        givenAnEmptyPriceRepository();

        Assertions.assertThrows(PriceNotFoundException.class, this::whenGetPrice);
    }

    private void givenAValidPriceData() {
        priceData = new GetPriceData(BRAND_ID, PRODUCT_ID, Date.valueOf(LocalDate.of(2022,8,5)));
    }

    private void givenAPriceRepository() {
        expectedPrices = new ArrayList<>();
        expectedPrices.add(new PriceItem(BRAND_ID, Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), PRICE_LIST_1, PRODUCT_ID, PRIORITY, BigDecimal.TEN, CURRENCY));
        Mockito.when(pricesRepository.findByDate(priceData.getBrandId(), priceData.getProductId(), priceData.getDate())).thenReturn(expectedPrices);
    }

    private void givenAPriceRepositoryWith2Results() {
        expectedPrices = new ArrayList<>();
        expectedPrices.add(new PriceItem(BRAND_ID, Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), PRICE_LIST_2, PRODUCT_ID, PRIORITY, BigDecimal.ONE, CURRENCY));
        expectedPrices.add(new PriceItem(BRAND_ID, Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), PRICE_LIST_1, PRODUCT_ID, PRIORITY, BigDecimal.TEN, CURRENCY));
        Mockito.when(pricesRepository.findByDate(priceData.getBrandId(), priceData.getProductId(), priceData.getDate())).thenReturn(expectedPrices);
    }

    private void givenAnEmptyPriceRepository() {
        expectedPrices = new ArrayList<>();
        Mockito.when(pricesRepository.findByDate(priceData.getBrandId(), priceData.getProductId(), priceData.getDate())).thenReturn(expectedPrices);
    }

    private void whenGetPrice() throws Throwable {
        actualPrice = pricesAction.getPrice(priceData);
    }

    private void shouldReturnAPrice() {
        Assertions.assertEquals(expectedPrices.get(0).getPrice(), actualPrice.getPrice());
        Assertions.assertEquals(expectedPrices.get(0).getPriceList(), actualPrice.getPriceList());
        Assertions.assertEquals(expectedPrices.get(0).getBrandId(), actualPrice.getBrandId());
        Assertions.assertEquals(expectedPrices.get(0).getProductId(), actualPrice.getProductId());
    }

}