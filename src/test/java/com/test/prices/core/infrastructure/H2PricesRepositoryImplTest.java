package com.test.prices.core.infrastructure;

import com.test.prices.core.domain.GetPriceData;
import com.test.prices.core.domain.Price;
import com.test.prices.core.domain.PricesRepository;
import com.test.prices.utils.DateFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class H2PricesRepositoryImplTest {
    private PricesRepository pricesRepository;
    private GetPriceData priceData;
    private Price price;
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        entityManager = mock(EntityManager.class);
        pricesRepository = new H2PricesRepositoryImpl();
    }

    @Test
    public void givenAPriceBetweenTwoRangesWhenGetPriceShouldReturnHigherListPrice() throws Throwable {
        givenPriceData(Date.from(LocalDateTime.of(2020,6,14, 16, 0, 0).toInstant(ZoneOffset.UTC)));
        givenEntityManager();

        whenPriceData();

        shouldReturnPrice();
    }

    @Test
    public void givenAPriceDataWithOneRangeWhenGetPriceShouldReturnUniquePrice() throws Throwable {
        givenPriceData(Date.from(LocalDateTime.of(2020,6,14, 19, 0, 0).toInstant(ZoneOffset.UTC)));
        givenEntityManager();

        whenPriceData();

        shouldReturnPrice();
    }

    private void givenEntityManager() throws ParseException {
        PriceItem item1 = new PriceItem(1, DateFormatter.toDate("2020-06-14-00.00.00"), DateFormatter.toDate("2020-12-31-23.59.59"),1, 35455, 0, BigDecimal.valueOf(35.50), "EUR");
        PriceItem item2 = new PriceItem(1, DateFormatter.toDate("2020-06-14-15.00.00"), DateFormatter.toDate("2020-06-14-18.30.00"),2, 35455, 1, BigDecimal.valueOf(25.45), "EUR");
        PriceItem item3 = new PriceItem(1, DateFormatter.toDate("2020-06-15-00.00.00"), DateFormatter.toDate("2020-06-15-11.00.00"),3, 35455, 1, BigDecimal.valueOf(30.50), "EUR");
        PriceItem item4 = new PriceItem(1, DateFormatter.toDate("2020-06-15-16.00.00"), DateFormatter.toDate("2020-12-31-23.59.59"),4, 35455, 1, BigDecimal.valueOf(38.95), "EUR");

        entityManager.persist(item1);
        entityManager.persist(item2);
        entityManager.persist(item3);
        entityManager.persist(item4);

    }

    private void givenPriceData(Date date) {
        priceData = new GetPriceData(1,35455, date);
    }

    private void whenPriceData() throws Throwable {
        price = pricesRepository.getPrice(priceData);
    }

    private void shouldReturnPrice() {
        assertEquals(BigDecimal.valueOf(25.45), price.getPrice());
        assertEquals(1, price.getBrandId());
        assertEquals(35455, price.getProductId());
        assertEquals(2, price.getPriceList());
    }
}