package com.app.portfolio.service.publisher;

import com.app.portfolio.event.PortfolioValueUpdateEvent;
import com.app.portfolio.service.PortfolioItem;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioResultPublisher {

    @EventListener
    public void handlePortfolioValueUpdate(PortfolioValueUpdateEvent event) {
        double totalNAV = event.getTotalNAV();
        List<PortfolioItem> portfolioItems = event.getPortfolioItems();

        System.out.println("Portfolio update: ");
        System.out.println("--------------------------------------------------");
        System.out.println("| Ticker            | Position Size | Price  | Market Value |");
        System.out.println("--------------------------------------------------");

        for (PortfolioItem item : portfolioItems) {
            String ticker = item.getSecurity().getTicker();
            int positionSize = item.getPositionSize();
            double marketValue = item.getMarketValue();
            double price = marketValue / positionSize;

            System.out.printf("| %-18s | %-13d | %-6.2f | %-12.2f |%n",
                    ticker,
                    positionSize,
                    price,
                    marketValue);
        }

        System.out.println("--------------------------------------------------");
        System.out.printf("| Total NAV: %-36.2f |%n", totalNAV);
        System.out.println("--------------------------------------------------");
        System.out.println("\n \n");
    }
}
