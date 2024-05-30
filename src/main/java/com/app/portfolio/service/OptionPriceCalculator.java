package com.app.portfolio.service;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.springframework.stereotype.Service;

@Service
public class OptionPriceCalculator {

    private static final NormalDistribution NORMAL = new NormalDistribution();
    private static final double RISK_FREE_RATE = 0.02;

    public static double calculateCallPrice(double S, double K, double T, double sigma) {
        double d1 = (Math.log(S / K) + (RISK_FREE_RATE + 0.5 * Math.pow(sigma, 2)) * T) / (sigma * Math.sqrt(T));
        double d2 = d1 - sigma * Math.sqrt(T);
        return S * NORMAL.cumulativeProbability(d1) - K * Math.exp(-RISK_FREE_RATE * T) * NORMAL.cumulativeProbability(d2);
    }

    public static double calculatePutPrice(double S, double K, double T, double sigma) {
        double d1 = (Math.log(S / K) + (RISK_FREE_RATE + 0.5 * Math.pow(sigma, 2)) * T) / (sigma * Math.sqrt(T));
        double d2 = d1 - sigma * Math.sqrt(T);
        return K * Math.exp(-RISK_FREE_RATE * T) * NORMAL.cumulativeProbability(-d2) - S * NORMAL.cumulativeProbability(-d1);
    }
}
