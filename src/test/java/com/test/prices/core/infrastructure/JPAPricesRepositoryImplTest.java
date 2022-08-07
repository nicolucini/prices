package com.test.prices.core.infrastructure;

import com.test.prices.core.domain.GetPriceData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class JPAPricesRepositoryImplTest {
    public static final Long PRODUCT_ID = 35455L;
    public static final Long BRAND_ID = 1L;
    public static final double PRICE_1 = 25.45;
    public static final double PRICE_2 = 35.50;
    public static final int PRICE_LIST_2 = 2;
    public static final int PRICE_LIST_1 = 1;

    @Autowired
    private JPAPricesRepository jpaPricesRepository;

    private GetPriceData priceData;
    private List<PriceItem> prices;

    @Test
    public void givenADateBetweenTwoRangesWhenGetPriceShouldReturnTwoPrices() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.JUNE, 14, 16, 0, 0);
        givenPriceData(calendar.getTime());

        whenFindByDate();

        shouldReturnTwoPrices();
    }

    @Test
    public void givenAPriceDataWithOneRangeWhenGetPriceShouldReturnUniquePrice() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.JUNE, 14, 10, 0, 0);
        givenPriceData(calendar.getTime());

        whenFindByDate();

        shouldReturnOnePrice();
    }

    private void givenPriceData(Date date) {
        priceData = new GetPriceData(BRAND_ID, PRODUCT_ID, date);
    }

    private void whenFindByDate() {
        prices = jpaPricesRepository.findByDate(priceData.getBrandId(), priceData.getProductId(), priceData.getDate());
    }

    private void shouldReturnTwoPrices() {
        assertEquals(2, prices.size());

        assertEquals(BigDecimal.valueOf(PRICE_1).doubleValue(), prices.get(0).getPrice().doubleValue());
        Assertions.assertEquals(BRAND_ID, prices.get(0).getBrandId());
        Assertions.assertEquals(PRODUCT_ID, prices.get(0).getProductId());
        assertEquals(PRICE_LIST_2, prices.get(0).getPriceList());

        assertEquals(BigDecimal.valueOf(PRICE_2).doubleValue(), prices.get(1).getPrice().doubleValue());
        assertEquals(BRAND_ID, prices.get(1).getBrandId());
        assertEquals(PRODUCT_ID, prices.get(1).getProductId());
        assertEquals(PRICE_LIST_1, prices.get(1).getPriceList());
    }

    private void shouldReturnOnePrice() {
        assertEquals(1, prices.size());

        assertEquals(BigDecimal.valueOf(PRICE_2).doubleValue(), prices.get(0).getPrice().doubleValue());
        assertEquals(BRAND_ID, prices.get(0).getBrandId());
        assertEquals(PRODUCT_ID, prices.get(0).getProductId());
        assertEquals(PRICE_LIST_1, prices.get(0).getPriceList());
    }


}