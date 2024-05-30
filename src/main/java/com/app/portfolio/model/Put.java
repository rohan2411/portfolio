package com.app.portfolio.model;

import com.app.portfolio.service.OptionPriceCalculator;

import javax.persistence.Entity;

@Entity
public class Put extends Option {

    @Override
    public double getPrice() {
        double timeToMaturity = (getMaturity().getTime() - System.currentTimeMillis()) / 31536000000.0; // convert milliseconds to years
        return OptionPriceCalculator.calculatePutPrice(getUnderlyingStock().getPrice(), getStrike(), timeToMaturity, getVolatility());
    }
}
