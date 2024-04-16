package com.example.coen_project.error_injection;

import java.io.IOException;
import java.util.Random;

public class ErrorInjection {
    private static final double FIVE_PERCENT = 0.05;

    public static void injectError() throws IOException {
        double random = new Random().nextDouble();
        if (random <= FIVE_PERCENT) {
            throw new IOException("Simulated 5% exception");
        }
    }
}

