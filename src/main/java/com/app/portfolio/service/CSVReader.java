package com.app.portfolio.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class CSVReader {

    private final ResourceLoader resourceLoader;

    public CSVReader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Map<String, Integer> readPositions(String filename) {
        Map<String, Integer> positions = new HashMap<>();
        try {
            Resource resource = resourceLoader.getResource("classpath:data/" + filename);
            InputStream inputStream = resource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Read the CSV file line by line
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String symbol = parts[0].trim();
                    try {
                        int positionSize = Integer.parseInt(parts[1].trim());
                        positions.put(symbol, positionSize);
                    } catch (NumberFormatException e) {
                        // Log a warning message for invalid position size
                        System.err.println("Invalid position size for symbol " + symbol + ": " + parts[1].trim());
                    }
                } else {
                    // Log a warning message for invalid line format
                    System.err.println("Invalid line format: " + line);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return positions;
    }
}
