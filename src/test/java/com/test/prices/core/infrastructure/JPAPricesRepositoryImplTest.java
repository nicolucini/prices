package com.test.prices.core.infrastructure;

import com.test.prices.core.domain.GetPriceData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class JPAPricesRepositoryImplTest {
    public static final Long PRODUCT_ID = 35455L;
    public static final Long BRAND_ID = 1L;
    public static final double PRICE_2 = 35.50;
    public static final int PRICE_LIST_1 = 1;

    @Autowired
    private H2PricesRepository jpaPricesRepository;

    private GetPriceData priceData;
    private Optional<PriceItem> priceItemOptional;

    @Test
    public void givenAPriceDataWithOneRangeWhenGetPriceShouldReturnUniquePrice() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.JUNE, 14, 10, 0, 0);
        givenPriceData(calendar.getTime());

        whenFindByDate();

        shouldReturnOnePrice();
    }

    private void givenPriceData(Date date) {
        priceData = new GetPriceData(BRAND_ID, PRODUCT_ID, date);
    }

    private void whenFindByDate() throws Exception {
        priceItemOptional = jpaPricesRepository.findByDate(priceData.getBrandId(), priceData.getProductId(), priceData.getDate());
    }


    private void shouldReturnOnePrice() {
        assertTrue(priceItemOptional.isPresent());
        assertEquals(BigDecimal.valueOf(PRICE_2).doubleValue(), priceItemOptional.get().getPrice().doubleValue());
        assertEquals(BRAND_ID, priceItemOptional.get().getBrandId());
        assertEquals(PRODUCT_ID, priceItemOptional.get().getProductId());
        assertEquals(PRICE_LIST_1, priceItemOptional.get().getPriceList());
    }


}