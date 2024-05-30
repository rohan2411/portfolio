package com.app.portfolio.event;

import com.app.portfolio.service.PortfolioItem;

import java.util.List;

public class PortfolioValueUpdateEvent {
    private final double totalNAV;
    private final List<PortfolioItem> portfolioItems;

    public PortfolioValueUpdateEvent(double totalNAV, List<PortfolioItem> portfolioItems) {
        this.totalNAV = totalNAV;
        this.portfolioItems = portfolioItems;
    }

    public double getTotalNAV() {
        return totalNAV;
    }

    public List<PortfolioItem> getPortfolioItems() {
        return portfolioItems;
    }
}
