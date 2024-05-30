package com.app.portfolio;

import com.app.portfolio.model.Stock;
import com.app.portfolio.repository.SecurityRepository;
import com.app.portfolio.service.MarketDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

class MarketDataProviderTest {

    @Mock
    private SecurityRepository securityRepository;

    @InjectMocks
    private MarketDataProvider marketDataProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPriceChange() {
        // Given
        Stock stock = new Stock();
        stock.setTicker("AAPL");
        stock.setCurrentPrice(150.0);
        stock.setExpectedReturn(0.05);
        stock.setVolatility(0.02);

        long secondsSinceLastUpdate = 3600; // 1 hour

        double priceChange = marketDataProvider.getPriceChange(stock, secondsSinceLastUpdate);

        assertNotEquals(150.0, 150.0 + priceChange);
    }
}
