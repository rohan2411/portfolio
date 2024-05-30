package com.app.portfolio;

import com.app.portfolio.model.Call;
import com.app.portfolio.model.Put;
import com.app.portfolio.model.Stock;
import com.app.portfolio.repository.SecurityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class DataInitializer implements CommandLineRunner {

    private final SecurityRepository securityRepository;

    public DataInitializer(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Create and save stocks
        Stock appleStock = new Stock();
        appleStock.setTicker("AAPL");
        appleStock.setExpectedReturn(0.05);
        appleStock.setVolatility(0.2);
        appleStock.setStartOfDayPrice(150.0); // Set start-of-day price
        appleStock.setCurrentPrice(150.0); // Set initial current price
        securityRepository.save(appleStock);

        Stock teslaStock = new Stock();
        teslaStock.setTicker("TLSA");
        teslaStock.setExpectedReturn(0.07);
        teslaStock.setVolatility(0.3);
        teslaStock.setStartOfDayPrice(700.0); // Set start-of-day price
        teslaStock.setCurrentPrice(700.0); // Set initial current price
        securityRepository.save(teslaStock);

        Stock msftStock = new Stock();
        msftStock.setTicker("MSFT");
        msftStock.setExpectedReturn(0.06);
        msftStock.setVolatility(0.2);
        msftStock.setStartOfDayPrice(400.0); // Set start-of-day price
        msftStock.setCurrentPrice(400.0); // Set initial current price
        securityRepository.save(msftStock);

        // Create and save call options
        Call appleCallOption = new Call();
        appleCallOption.setTicker("AAPL-OCT-2020-110-C");
        appleCallOption.setStrike(110);
        appleCallOption.setUnderlyingStock(appleStock);
        appleCallOption.setMaturity(sdf.parse("2024-10-01"));
        appleCallOption.setExpectedReturn(0.05);
        appleCallOption.setVolatility(0.2);
        securityRepository.save(appleCallOption);


        Call teslaCallOption = new Call();
        teslaCallOption.setTicker("TLSA-NOV-2020-400-C");
        teslaCallOption.setStrike(400);
        teslaCallOption.setUnderlyingStock(teslaStock);
        teslaCallOption.setMaturity(sdf.parse("2024-11-01"));
        teslaCallOption.setExpectedReturn(0.07);
        teslaCallOption.setVolatility(0.3);
        securityRepository.save(teslaCallOption);

        Call msftCallOption = new Call();
        msftCallOption.setTicker("MSFT-OCT-2020-100-C");
        msftCallOption.setStrike(400);
        msftCallOption.setUnderlyingStock(msftStock);
        msftCallOption.setMaturity(sdf.parse("2024-10-01"));
        msftCallOption.setExpectedReturn(0.08);
        msftCallOption.setVolatility(0.3);
        securityRepository.save(msftCallOption);

        // Create and save put options
        Put applePutOption = new Put();
        applePutOption.setTicker("AAPL-OCT-2020-110-P");
        applePutOption.setStrike(110);
        applePutOption.setUnderlyingStock(appleStock);
        applePutOption.setMaturity(sdf.parse("2024-10-01"));
        applePutOption.setExpectedReturn(0.05);
        applePutOption.setVolatility(0.3);
        securityRepository.save(applePutOption);

        Put teslaPutOption = new Put();
        teslaPutOption.setTicker("TLSA-DEC-2020-400-P");
        teslaPutOption.setStrike(400);
        teslaPutOption.setUnderlyingStock(teslaStock);
        teslaPutOption.setMaturity(sdf.parse("2024-12-01"));
        teslaPutOption.setExpectedReturn(0.07);
        teslaPutOption.setVolatility(0.3);
        securityRepository.save(teslaPutOption);

        Put msftPutOption = new Put();
        msftPutOption.setTicker("MSFT-NOV-2020-200-P");
        msftPutOption.setStrike(200);
        msftPutOption.setUnderlyingStock(msftStock);
        msftPutOption.setMaturity(sdf.parse("2024-11-01"));
        msftPutOption.setExpectedReturn(0.07);
        msftPutOption.setVolatility(0.3);
        securityRepository.save(msftPutOption);
    }
}

