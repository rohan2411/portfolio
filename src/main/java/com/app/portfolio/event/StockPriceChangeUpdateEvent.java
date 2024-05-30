package com.app.portfolio.event;

import com.app.portfolio.model.Stock;

public class StockPriceChangeUpdateEvent {
    private final Stock stock;
    private final double change;
    private final double newPrice;

    public StockPriceChangeUpdateEvent(Stock stock, double change, double newPrice) {
        this.stock = stock;
        this.change = change;
        this.newPrice = newPrice;
    }

    public Stock getStock() {
        return stock;
    }

    public double getChange() {
        return change;
    }

    public double getNewPrice() {
        return newPrice;
    }
}
