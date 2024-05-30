package com.app.portfolio.service;

import com.app.portfolio.event.PortfolioValueUpdateEvent;
import com.app.portfolio.model.Security;
import com.app.portfolio.repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PortfolioManager {

    private final SecurityRepository securityRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final Map<String, Integer> positions;

    public PortfolioManager(SecurityRepository securityRepository,
                            ApplicationEventPublisher eventPublisher,
                            CSVReader csvPositionReader,
                            @Value("${positions.filepath}") String positionsFilePath) {
        this.securityRepository = securityRepository;
        this.eventPublisher = eventPublisher;
        this.positions = csvPositionReader.readPositions(positionsFilePath);
    }

    @EventListener
    public void onMarketDataUpdate(MarketDataProvider.MarketDataUpdateEvent event) {
        updatePortfolioValues();
    }

    public void updatePortfolioValues() {
        double totalNAV = 0;
        List<PortfolioItem> portfolioItems = new ArrayList<>();
        boolean allSecuritiesFound = true;

        for (Map.Entry<String, Integer> entry : positions.entrySet()) {
            String ticker = entry.getKey();
            int positionSize = entry.getValue();
            Security security = securityRepository.findById(ticker).orElse(null);
            if (security != null) {
                double price = security.getPrice();
                double marketValue = positionSize * price;
                totalNAV += marketValue;
                portfolioItems.add(new PortfolioItem(security, marketValue, positionSize));
            } else {
                // Security not found, set flag to false
                allSecuritiesFound = false;
                System.out.println("Security with ticker " + ticker + " not found.");
            }
        }

        // Only publish event if all securities were found
        if (allSecuritiesFound) {
            eventPublisher.publishEvent(new PortfolioValueUpdateEvent(totalNAV, portfolioItems));
        }
    }
}
