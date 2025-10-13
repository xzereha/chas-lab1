package com.example;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.trace("Testing trace");
        logger.debug("Testing debug");
        logger.info("Testing info");
        logger.warn("Testing a warning {}", 42);
        logger.error("Testing an error", new RuntimeException("Test"));
        logger.info("Starting demo...");
        new Demo().run();
        logger.info("Demo finished.");
    }
}