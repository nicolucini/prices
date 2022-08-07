package com.test.prices.core.infrastructure;

import com.test.prices.core.domain.GetPriceResponseData;
import com.test.prices.core.domain.PricesRepository;
import com.test.prices.core.domain.exception.PriceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class H2PricesRepository implements PricesRepository {
    @Autowired
    private JPAPricesRepository jpaPricesRepository;

    public H2PricesRepository(JPAPricesRepository jpaPricesRepository) {
        this.jpaPricesRepository = jpaPricesRepository;
    }

    @Override
    public GetPriceResponseData findByDate(Long brandId, Long productId, Date date) throws PriceNotFoundException {
        return jpaPricesRepository.findByDate(brandId, productId, date)
                .stream().findFirst().
                orElseThrow(PriceNotFoundException::new)
                .toPrice();
    }
}
