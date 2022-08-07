package com.test.prices.core.infrastructure;

import com.test.prices.core.domain.PricesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.Optional;

public interface H2PricesRepository extends CrudRepository<PriceItem, Long>, PricesRepository {
    @Query(value = "SELECT * FROM prices p WHERE p.brand_id = ?1 and p.product_id = ?2 and p.start_date <= ?3 and p.end_date >= ?3 order by p.priority desc LIMIT 1", nativeQuery = true)
    Optional<PriceItem> findByDate(Long brandId, Long productId, Date date) throws Exception;

}
