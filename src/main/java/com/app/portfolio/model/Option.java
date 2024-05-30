package com.app.portfolio.model;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
public abstract class Option extends Security implements Serializable {

    private Stock underlyingStock;
    private double strike;
    private Date maturity;

    // Getters and Setters
    public Stock getUnderlyingStock() {
        return underlyingStock;
    }

    public void setUnderlyingStock(Stock underlyingStock) {
        this.underlyingStock = underlyingStock;
    }

    public double getStrike() {
        return strike;
    }

    public void setStrike(double strike) {
        this.strike = strike;
    }

    public Date getMaturity() {
        return maturity;
    }

    public void setMaturity(Date maturity) {
        this.maturity = maturity;
    }
}
