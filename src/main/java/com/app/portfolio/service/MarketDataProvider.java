package com.app.portfolio.service;

import com.app.portfolio.event.StockPriceChangeUpdateEvent;
import com.app.portfolio.model.Stock;
import com.app.portfolio.repository.SecurityRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MarketDataProvider {

    private static final Random RANDOM = new Random();
    private final ApplicationEventPublisher eventPublisher;
    private final SecurityRepository securityRepository;
    private final Map<String, Instant> lastUpdateTimes = new ConcurrentHashMap<>();

    public MarketDataProvider(ApplicationEventPublisher eventPublisher, SecurityRepository securityRepository) {
        this.eventPublisher = eventPublisher;
        this.securityRepository = securityRepository;
    }

    @Scheduled(fixedDelayString = "#{T(java.util.concurrent.ThreadLocalRandom).current().nextInt(500, 2001)}")
    public void updateMarketData() {
        List<Stock> stocks = securityRepository.findByStartOfDayPriceNotNull();
        Instant now = Instant.now();

        for (Stock stock : stocks) {
            Instant lastUpdate = lastUpdateTimes.getOrDefault(stock.getTicker(), now);
            long secondsSinceLastUpdate = ChronoUnit.SECONDS.between(lastUpdate, now);
            lastUpdateTimes.put(stock.getTicker(), now);

            double priceChange = getPriceChange(stock, secondsSinceLastUpdate);
            double newPrice = stock.getCurrentPrice() + priceChange;

            if (priceChange != 0.0) {
                stock.setCurrentPrice(newPrice);
                securityRepository.save(stock);
                eventPublisher.publishEvent(new MarketDataUpdateEvent(this, stock));
                eventPublisher.publishEvent(new StockPriceChangeUpdateEvent(stock, priceChange, newPrice));
            }
            }
    }

    public double getPriceChange(Stock stock, long secondsSinceLastUpdate) {
        double currentPrice = stock.getCurrentPrice();
        double expectedReturn = stock.getExpectedReturn();
        double volatility = stock.getVolatility();
        double dt = (double) secondsSinceLastUpdate / 7257600;
        double epsilon = RANDOM.nextGaussian();
        return currentPrice * (expectedReturn * dt + volatility * epsilon * Math.sqrt(dt));
    }

    public static class MarketDataUpdateEvent {
        private final Object source;
        private final Stock stock;

        public MarketDataUpdateEvent(Object source, Stock stock) {
            this.source = source;
            this.stock = stock;
        }

        public Stock getStock() {
            return stock;
        }

        public Object getSource() {
            return source;
        }
    }
}
