package com.logger;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger {
    private static FileLogger instance = null;
    private static final Object instanceLock = new Object();
    private final Object fileLock = new Object();
    private final String logPath;

    private FileLogger() {
        String dateFormat = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm"));
        this.logPath = String.format("logs/log-%s.log", dateFormat);
    }

    public static FileLogger getInstance() {
        if (instance == null) {
            // Lock so two threads can't create the instance at the same time
            synchronized (instanceLock) {
                if (instance == null) {
                    instance = new FileLogger();
                }
            }
        }
        return instance;
    }

    public void log(String message) {
        // Locking the file so two threads can't write to it at the same time
        synchronized (fileLock) {
            // Create the log directory if it doesn't exist
            if (!Files.exists(Paths.get("logs"))) {
                try {
                    Files.createDirectories(Paths.get("logs"));
                } catch (Exception e) {
                    System.err.println("Failed to create log directory: " + e.getMessage());
                    // e.printStackTrace();
                    return;
                }
            }
            // Write the log message
            try {
                Files.write(Paths.get(logPath), message.getBytes(),
                        StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (Exception e) {
                System.err.println("Failed to write to log file: " + e.getMessage());
                // e.printStackTrace();
            }
        }
    }
}
