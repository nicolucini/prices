package com.test.prices.core.infrastructure;

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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.mockito.Mockito.mock;

class H2PricesRepositoryTest {
    public static final long BRAND_ID = 1L;
    public static final long PRODUCT_ID = 2L;
    public static final int PRIORITY = 1;
    public static final long PRICE_LIST_1 = 1L;
    public static final long PRICE_LIST_2 = 2L;
    public static final String CURRENCY = "EUR";

    private PricesRepository pricesRepository;
    @Mock
    private JPAPricesRepository jpaPricesRepository;

    private GetPriceData priceData;
    private GetPriceResponseData actualPrice;
    private List<PriceItem> expectedPrices;

    @BeforeEach
    void setUp() {
        jpaPricesRepository = mock(JPAPricesRepository.class);
        pricesRepository = new H2PricesRepository(jpaPricesRepository);
    }

    @Test
    public void givenAActionDataWhenGetPriceShouldReturnAPrice() throws Throwable {
        givenAValidPriceData();
        givenAJPARepository();

        whenFindPrice();

        shouldReturnAPrice();
    }

    @Test
    public void givenAActionDataBetweenTwoPricesWhenGetPriceShouldReturnFirstResult() throws Throwable {
        givenAValidPriceData();
        givenAJPARepositoryWith2Results();

        whenFindPrice();

        shouldReturnAPrice();
    }

    @Test
    public void givenAActionDataThatNotMatchWithAnyPriceWhenGetPriceShouldReturnPriceNotFoundException() {
        givenAValidPriceData();
        givenAnEmptyJPARepository();

        Assertions.assertThrows(PriceNotFoundException.class, this::whenFindPrice);
    }

    private void givenAValidPriceData() {
        priceData = new GetPriceData(BRAND_ID, PRODUCT_ID, Date.valueOf(LocalDate.of(2022,8,5)));
    }

    private void givenAJPARepository() {
        expectedPrices = new ArrayList<>();
        expectedPrices.add(new PriceItem(BRAND_ID, Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), PRICE_LIST_1, PRODUCT_ID, PRIORITY, BigDecimal.TEN, CURRENCY));
        Mockito.when(jpaPricesRepository.findByDate(priceData.getBrandId(), priceData.getProductId(), priceData.getDate())).thenReturn(expectedPrices);
    }

    private void givenAJPARepositoryWith2Results() {
        expectedPrices = new ArrayList<>();
        expectedPrices.add(new PriceItem(BRAND_ID, Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), PRICE_LIST_2, PRODUCT_ID, PRIORITY, BigDecimal.ONE, CURRENCY));
        expectedPrices.add(new PriceItem(BRAND_ID, Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), PRICE_LIST_1, PRODUCT_ID, PRIORITY, BigDecimal.TEN, CURRENCY));
        Mockito.when(jpaPricesRepository.findByDate(priceData.getBrandId(), priceData.getProductId(), priceData.getDate())).thenReturn(expectedPrices);
    }

    private void givenAnEmptyJPARepository() {
        expectedPrices = new ArrayList<>();
        Mockito.when(jpaPricesRepository.findByDate(priceData.getBrandId(), priceData.getProductId(), priceData.getDate())).thenReturn(expectedPrices);
    }

    private void whenFindPrice() throws Throwable {
        actualPrice = pricesRepository.findByDate(priceData.getBrandId(), priceData.getProductId(), priceData.getDate());
    }

    private void shouldReturnAPrice() {
        Assertions.assertEquals(expectedPrices.get(0).getPrice(), actualPrice.getPrice());
        Assertions.assertEquals(expectedPrices.get(0).getPriceList(), actualPrice.getPriceList());
        Assertions.assertEquals(expectedPrices.get(0).getBrandId(), actualPrice.getBrandId());
        Assertions.assertEquals(expectedPrices.get(0).getProductId(), actualPrice.getProductId());
    }
}