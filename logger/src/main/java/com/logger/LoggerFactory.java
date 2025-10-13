package com.logger;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public class LoggerFactory implements ILoggerFactory {

    @Override
    public Logger getLogger(String name) {
        return new MyLogger(name);
    }

}
