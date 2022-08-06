package com.test.prices.core.infrastructure;

import com.test.prices.core.domain.GetPriceData;
import com.test.prices.core.domain.Price;
import com.test.prices.core.domain.exception.PriceNotFoundException;
import com.test.prices.core.domain.PricesRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class H2PricesRepositoryImpl implements PricesRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Price getPrice(GetPriceData priceData) throws Throwable {
        String queryString = String.format("select * from prices where brand_id = %s " +
                        "and product_id = %s and start_date <= %s " +
                        "and end_date >= %s order by priority desc",
                priceData.getBrandId(),
                priceData.getProductId(), priceData.getDate(),
                priceData.getDate());
        Query query = entityManager.createQuery(
                queryString);
        return (Price) query.getResultList().stream().findFirst().orElseThrow(PriceNotFoundException::new);
    }

}
