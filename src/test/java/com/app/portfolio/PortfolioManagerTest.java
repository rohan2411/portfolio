package com.app.portfolio.service;

import com.app.portfolio.event.PortfolioValueUpdateEvent;
import com.app.portfolio.model.Security;
import com.app.portfolio.model.Stock;
import com.app.portfolio.repository.SecurityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.context.ApplicationEventPublisher;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PortfolioManagerTest {

    private SecurityRepository securityRepository;
    private ApplicationEventPublisher eventPublisher;
    private PortfolioManager portfolioManager;

    @BeforeEach
    void setUp() {
        securityRepository = mock(SecurityRepository.class);
        eventPublisher = mock(ApplicationEventPublisher.class);
        CSVReader csvReader = mock(CSVReader.class);

        Map<String, Integer> positions = new HashMap<>();
        positions.put("AAPL", 10);
        positions.put("TSLA", 5);

        when(csvReader.readPositions(anyString())).thenReturn(positions);

        portfolioManager = new PortfolioManager(securityRepository, eventPublisher, csvReader, "positions.csv");
    }

    @Test
    void testUpdatePortfolioValues() {
        Stock appleStock = new Stock();
        appleStock.setTicker("AAPL");
        appleStock.setCurrentPrice(150.0);

        Stock teslaStock = new Stock();
        teslaStock.setTicker("TSLA");
        teslaStock.setCurrentPrice(700.0);

        when(securityRepository.findById("AAPL")).thenReturn(Optional.of(appleStock));
        when(securityRepository.findById("TSLA")).thenReturn(Optional.of(teslaStock));

        portfolioManager.updatePortfolioValues();

        ArgumentCaptor<PortfolioValueUpdateEvent> eventCaptor = ArgumentCaptor.forClass(PortfolioValueUpdateEvent.class);
        verify(eventPublisher, times(1)).publishEvent(eventCaptor.capture());

        PortfolioValueUpdateEvent event = eventCaptor.getValue();
        assertEquals(10 * 150.0 + 5 * 700.0, event.getTotalNAV(), 0.001);
        assertEquals(2, event.getPortfolioItems().size());
    }

    @Test
    void testSecurityNotFound() {
        when(securityRepository.findById("AAPL")).thenReturn(Optional.empty());
        when(securityRepository.findById("TSLA")).thenReturn(Optional.of(new Stock()));

        portfolioManager.updatePortfolioValues();

        verify(eventPublisher, never()).publishEvent(any(PortfolioValueUpdateEvent.class));
    }
}
