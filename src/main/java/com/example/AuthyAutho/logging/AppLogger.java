package com.example.AuthyAutho.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Custom logger wrapper that provides a .NET-style logging API.
 * Backed by SLF4J / Logback (included with Spring Boot).
 *
 * Usage:
 *   private final AppLogger _logger = new AppLogger(MyClass.class);
 *   _logger.logInformation("Something happened");
 */
public class AppLogger {

    private final Logger logger;

    public AppLogger(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    // ── Information ────────────────────────────────────────────────────────────

    public void logInformation(String message) {
        logger.info(message);
    }

    public void logInformation(String message, Object... args) {
        logger.info(message, args);
    }

    // ── Warning ────────────────────────────────────────────────────────────────

    public void logWarning(String message) {
        logger.warn(message);
    }

    public void logWarning(String message, Object... args) {
        logger.warn(message, args);
    }

    // ── Error ──────────────────────────────────────────────────────────────────

    public void logError(String message) {
        logger.error(message);
    }

    public void logError(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    public void logError(String message, Object... args) {
        logger.error(message, args);
    }

    // ── Debug ──────────────────────────────────────────────────────────────────

    public void logDebug(String message) {
        logger.debug(message);
    }

    public void logDebug(String message, Object... args) {
        logger.debug(message, args);
    }
}

