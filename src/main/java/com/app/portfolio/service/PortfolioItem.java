package com.app.portfolio.service;

import com.app.portfolio.model.Security;

public class PortfolioItem {
    private final Security security;
    private final double marketValue;
    private final int positionSize;

    public PortfolioItem(Security security, double marketValue, int positionSize) {
        this.security = security;
        this.marketValue = marketValue;
        this.positionSize = positionSize;
    }

    public Security getSecurity() {
        return security;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public int getPositionSize() {
        return positionSize;
    }
}
