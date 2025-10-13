package com.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Marker;

public class MyLogger implements org.slf4j.Logger {

    private enum Level {
        TRACE, DEBUG, INFO, WARN, ERROR;

        private String label() {
            return switch (this) {
                // Spaces are used to make all labels the same width
                case TRACE -> "TRACE";
                case DEBUG -> "DEBUG";
                case INFO -> "INFO ";
                case WARN -> "WARN ";
                case ERROR -> "ERROR";
            };
        }

        private String colorCode() {
            return switch (this) {
                case TRACE -> "\u001B[37m"; // White
                case DEBUG -> "\u001B[34m"; // Blue
                case INFO -> "\u001B[32m"; // Green
                case WARN -> "\u001B[33m"; // Yellow
                case ERROR -> "\u001B[31m"; // Red
            };
        }
    }

    private final String name;

    public MyLogger(String name) {
        this.name = name;
    }

    private String getTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("H:mm:ss"));
    }

    private void logMessage(Level level, String msg) {
        String timestamp = getTimestamp();
        // Formatting the message is [TIME LEVEL NAME] MSG
        // With color codes for the level so it stands out in the console
        System.out.printf("[%s %s%s\u001B[0m %s] %s\n", timestamp, level.colorCode(), level.label(), name, msg);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    @Override
    public void trace(String msg) {
        logMessage(Level.TRACE, msg);
    }

    // Helper to format messages
    private String formatMsg(String format, Object... args) {
        return String.format(format.replace("{}", "%s"), args);
    }

    @Override
    public void trace(String format, Object arg) {
        trace(formatMsg(format, arg));
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        trace(formatMsg(format, arg1, arg2));
    }

    @Override
    public void trace(String format, Object... arguments) {
        trace(formatMsg(format, arguments));
    }

    @Override
    public void trace(String msg, Throwable t) {
        trace(msg + "\n" + t);
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isTraceEnabled'");
    }

    @Override
    public void trace(Marker marker, String msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'trace'");
    }

    @Override
    public void trace(Marker marker, String format, Object arg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'trace'");
    }

    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'trace'");
    }

    @Override
    public void trace(Marker marker, String format, Object... argArray) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'trace'");
    }

    @Override
    public void trace(Marker marker, String msg, Throwable t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'trace'");
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public void debug(String msg) {
        logMessage(Level.DEBUG, msg);
    }

    @Override
    public void debug(String format, Object arg) {
        debug(String.format(format.replace("{}", "%s"), arg));
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        debug(String.format(format.replace("{}", "%s"), arg1, arg2));
    }

    @Override
    public void debug(String format, Object... arguments) {
        debug(String.format(format.replace("{}", "%s"), arguments));
    }

    @Override
    public void debug(String msg, Throwable t) {
        debug(msg);
        t.printStackTrace(System.out);
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isDebugEnabled'");
    }

    @Override
    public void debug(Marker marker, String msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'debug'");
    }

    @Override
    public void debug(Marker marker, String format, Object arg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'debug'");
    }

    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'debug'");
    }

    @Override
    public void debug(Marker marker, String format, Object... arguments) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'debug'");
    }

    @Override
    public void debug(Marker marker, String msg, Throwable t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'debug'");
    }

    @Override
    public boolean isInfoEnabled() {
        return true;
    }

    @Override
    public void info(String msg) {
        logMessage(Level.INFO, msg);
    }

    @Override
    public void info(String format, Object arg) {
        String formatted = String.format(format.replace("{}", "%s"), arg);
        info(formatted);
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        String formatted = String.format(format.replace("{}", "%s"), arg1, arg2);
        info(formatted);
    }

    @Override
    public void info(String format, Object... arguments) {
        String formatted = String.format(format.replace("{}", "%s"), arguments);
        info(formatted);
    }

    @Override
    public void info(String msg, Throwable t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'info'");
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isInfoEnabled'");
    }

    @Override
    public void info(Marker marker, String msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'info'");
    }

    @Override
    public void info(Marker marker, String format, Object arg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'info'");
    }

    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'info'");
    }

    @Override
    public void info(Marker marker, String format, Object... arguments) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'info'");
    }

    @Override
    public void info(Marker marker, String msg, Throwable t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'info'");
    }

    @Override
    public boolean isWarnEnabled() {
        return true;
    }

    @Override
    public void warn(String msg) {
        logMessage(Level.WARN, msg);
    }

    @Override
    public void warn(String format, Object arg) {
        warn(String.format(format.replace("{}", "%s"), arg));
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        warn(String.format(format.replace("{}", "%s"), arg1, arg2));
    }

    @Override
    public void warn(String format, Object... arguments) {
        warn(String.format(format.replace("{}", "%s"), arguments));
    }

    @Override
    public void warn(String msg, Throwable t) {
        warn(msg);
        t.printStackTrace(System.out);
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isWarnEnabled'");
    }

    @Override
    public void warn(Marker marker, String msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'warn'");
    }

    @Override
    public void warn(Marker marker, String format, Object arg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'warn'");
    }

    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'warn'");
    }

    @Override
    public void warn(Marker marker, String format, Object... arguments) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'warn'");
    }

    @Override
    public void warn(Marker marker, String msg, Throwable t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'warn'");
    }

    @Override
    public boolean isErrorEnabled() {
        return true;
    }

    @Override
    public void error(String msg) {
        logMessage(Level.ERROR, msg);
    }

    @Override
    public void error(String format, Object arg) {
        String formatted = String.format(format.replace("{}", "%s"), arg);
        error(formatted);
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        String formatted = String.format(format.replace("{}", "%s"), arg1, arg2);
        error(formatted);
    }

    @Override
    public void error(String format, Object... arguments) {
        String formatted = String.format(format.replace("{}", "%s"), arguments);
        error(formatted);
    }

    @Override
    public void error(String msg, Throwable t) {
        error(msg);
        t.printStackTrace(System.out);
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isErrorEnabled'");
    }

    @Override
    public void error(Marker marker, String msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'error'");
    }

    @Override
    public void error(Marker marker, String format, Object arg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'error'");
    }

    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'error'");
    }

    @Override
    public void error(Marker marker, String format, Object... arguments) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'error'");
    }

    @Override
    public void error(Marker marker, String msg, Throwable t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'error'");
    }

}