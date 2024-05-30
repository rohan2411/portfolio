package com.app.portfolio.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Security {

    @Id
    private String ticker;
    private double expectedReturn;
    private double volatility;

    // Getters and Setters

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public double getExpectedReturn() {
        return expectedReturn;
    }

    public void setExpectedReturn(double expectedReturn) {
        this.expectedReturn = expectedReturn;
    }

    public double getVolatility() {
        return volatility;
    }

    public void setVolatility(double volatility) {
        this.volatility = volatility;
    }

    public abstract double getPrice();

}

