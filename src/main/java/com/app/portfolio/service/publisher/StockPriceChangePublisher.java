package com.app.portfolio.service.publisher;

import com.app.portfolio.event.StockPriceChangeUpdateEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class StockPriceChangePublisher {

    @EventListener
    public void handleStockPriceChangeBatch(StockPriceChangeUpdateEvent event) {
        System.out.println("Stock Price Change:");
        System.out.printf("Stock %s price changed by %.6f to new price %.2f%n",
                    event.getStock().getTicker(),
                    event.getChange(),
                    event.getNewPrice());
        }

}
