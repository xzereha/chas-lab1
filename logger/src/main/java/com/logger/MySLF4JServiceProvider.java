package com.logger;

import org.slf4j.spi.SLF4JServiceProvider;
import org.slf4j.IMarkerFactory;

public class MySLF4JServiceProvider implements SLF4JServiceProvider {
    @Override
    public void initialize() {
        // No initialization needed for this
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
        throw new UnsupportedOperationException("Unimplemented method 'getMarkerFactory'");
    }

    @Override
    public org.slf4j.spi.MDCAdapter getMDCAdapter() {
        // No clue what this is for, so just return null
        return null;
    }
}
