package com.logger;

import org.slf4j.spi.SLF4JServiceProvider;
import org.slf4j.IMarkerFactory;

public class MySLF4JServiceProvider implements SLF4JServiceProvider {
    @Override
    public void initialize() {
        // Initialization logic for your logger
    }

    @Override
    public String getRequestedApiVersion() {
        return "2.0";
    }

    @Override
    public org.slf4j.ILoggerFactory getLoggerFactory() {
        return new LoggerFactory();
    }

    @Override
    public IMarkerFactory getMarkerFactory() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMarkerFactory'");
    }

    @Override
    public org.slf4j.spi.MDCAdapter getMDCAdapter() {
        // Return your custom MDCAdapter implementation
        return null;
    }
}
