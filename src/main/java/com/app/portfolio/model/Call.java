package com.app.portfolio.model;

import com.app.portfolio.service.OptionPriceCalculator;
import javax.persistence.Entity;

@Entity
public class Call extends Option {

    @Override
    public double getPrice() {
        double timeToMaturity = (getMaturity().getTime() - System.currentTimeMillis()) / 31536000000.0; // convert milliseconds to years
        return OptionPriceCalculator.calculateCallPrice(getUnderlyingStock().getCurrentPrice(), getStrike(), timeToMaturity, getVolatility());
    }
}
