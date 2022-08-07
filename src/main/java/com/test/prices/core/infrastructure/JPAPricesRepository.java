package com.test.prices.core.infrastructure;

import com.test.prices.core.domain.PricesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface JPAPricesRepository extends CrudRepository<PriceItem, Long> {
    @Query("SELECT p FROM PriceItem p WHERE p.brandId = ?1 and p.productId = ?2 and p.startDate <= ?3 and p.endDate >= ?3 order by p.priority desc")
    List<PriceItem> findByDate(Long brandId, Long productId, Date date);

}
