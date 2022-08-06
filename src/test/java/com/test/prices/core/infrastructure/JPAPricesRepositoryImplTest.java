package com.test.prices.core.infrastructure;

import com.test.prices.core.domain.GetPriceData;
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
    @Autowired
    private JPAPricesRepositoryImpl pricesRepository;
    private GetPriceData priceData;
    private List<PriceItem> prices;

    @Test
    public void givenADateBetweenTwoRangesWhenGetPriceShouldReturnTwoPrices() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.JUNE, 14, 16, 0, 0);
        givenPriceData(calendar.getTime());

        whenPriceData();

        shouldReturnTwoPrices();
    }

    @Test
    public void givenAPriceDataWithOneRangeWhenGetPriceShouldReturnUniquePrice() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.JUNE, 14, 10, 0, 0);
        givenPriceData(calendar.getTime());

        whenPriceData();

        shouldReturnOnePrice();
    }

    private void givenPriceData(Date date) {
        priceData = new GetPriceData(1,35455, date);
    }

    private void whenPriceData() {
        prices = pricesRepository.findByDate(priceData.getBrandId(), priceData.getProductId(), priceData.getDate());
    }

    private void shouldReturnTwoPrices() {
        assertEquals(2, prices.size());

        assertEquals(BigDecimal.valueOf(25.45).doubleValue(), prices.get(0).getPrice().doubleValue());
        assertEquals(1, prices.get(0).getBrandId());
        assertEquals(35455, prices.get(0).getProductId());
        assertEquals(2, prices.get(0).getPriceList());

        assertEquals(BigDecimal.valueOf(35.50).doubleValue(), prices.get(1).getPrice().doubleValue());
        assertEquals(1, prices.get(1).getBrandId());
        assertEquals(35455, prices.get(1).getProductId());
        assertEquals(1, prices.get(1).getPriceList());
    }

    private void shouldReturnOnePrice() {
        assertEquals(1, prices.size());

        assertEquals(BigDecimal.valueOf(35.50).doubleValue(), prices.get(0).getPrice().doubleValue());
        assertEquals(1, prices.get(0).getBrandId());
        assertEquals(35455, prices.get(0).getProductId());
        assertEquals(1, prices.get(0).getPriceList());
    }


}