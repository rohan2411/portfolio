package com.app.portfolio.model;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Stock extends Security implements Serializable {
    private double startOfDayPrice;
    private double currentPrice;

    // Getters and Setters

    public double getStartOfDayPrice() {
        return startOfDayPrice;
    }

    public void setStartOfDayPrice(double startOfDayPrice) {
        this.startOfDayPrice = startOfDayPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public double getPrice() {
        return currentPrice;
    }
}
