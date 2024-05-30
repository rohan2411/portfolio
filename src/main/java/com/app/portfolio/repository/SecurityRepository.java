package com.app.portfolio.repository;

import com.app.portfolio.model.Security;
import com.app.portfolio.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SecurityRepository extends JpaRepository<Security, String> {
    List<Stock> findByStartOfDayPriceNotNull();
    Optional<Stock> findByTicker(String ticker);
}
