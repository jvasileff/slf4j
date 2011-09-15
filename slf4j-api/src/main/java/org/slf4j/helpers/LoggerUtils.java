package org.slf4j.helpers;

import static org.slf4j.Level.DEBUG;
import static org.slf4j.Level.ERROR;
import static org.slf4j.Level.INFO;
import static org.slf4j.Level.TRACE;
import static org.slf4j.Level.WARN;

import org.slf4j.Level;
import org.slf4j.Logger;
import org.slf4j.Marker;

public class LoggerUtils {

  private LoggerUtils() {
    // no instances;
  }

  // TRACE

  public static void trace(String fqcn, Logger logger, String msg) {
    maybeLog(fqcn, logger, null, TRACE, msg, (Throwable) null);
  }

  public static void trace(String fqcn, Logger logger, String msg, Throwable t) {
    maybeLog(fqcn, logger, null, TRACE, msg, t);
  }

  public static void trace(String fqcn, Logger logger, String format,
      Object arg) {
    maybeLog(fqcn, logger, null, TRACE, format, arg);
  }

  public static void trace(String fqcn, Logger logger, String format,
      Object arg1, Object arg2) {
    maybeLog(fqcn, logger, null, TRACE, format, arg1, arg2);
  }

  public static void trace(String fqcn, Logger logger, String format,
      Object... args) {
    maybeLog(fqcn, logger, null, TRACE, format, args);
  }

  public static void trace(String fqcn, Logger logger, Marker marker,
      String msg) {
    maybeLog(fqcn, logger, marker, TRACE, msg, (Throwable) null);
  }

  public static void trace(String fqcn, Logger logger, Marker marker,
      String msg, Throwable t) {
    maybeLog(fqcn, logger, marker, TRACE, msg, t);
  }

  public static void trace(String fqcn, Logger logger, Marker marker,
      String format, Object arg) {
    maybeLog(fqcn, logger, marker, TRACE, format, arg);
  }

  public static void trace(String fqcn, Logger logger, Marker marker,
      String format, Object arg1, Object arg2) {
    maybeLog(fqcn, logger, marker, TRACE, format, arg1, arg2);
  }

  public static void trace(String fqcn, Logger logger, Marker marker,
      String format, Object... args) {
    maybeLog(fqcn, logger, marker, TRACE, format, args);
  }

  // DEBUG

  public static void debug(String fqcn, Logger logger, String msg) {
    maybeLog(fqcn, logger, null, DEBUG, msg, (Throwable) null);
  }

  public static void debug(String fqcn, Logger logger, String msg, Throwable t) {
    maybeLog(fqcn, logger, null, DEBUG, msg, t);
  }

  public static void debug(String fqcn, Logger logger, String format,
      Object arg) {
    maybeLog(fqcn, logger, null, DEBUG, format, arg);
  }

  public static void debug(String fqcn, Logger logger, String format,
      Object arg1, Object arg2) {
    maybeLog(fqcn, logger, null, DEBUG, format, arg1, arg2);
  }

  public static void debug(String fqcn, Logger logger, String format,
      Object... args) {
    maybeLog(fqcn, logger, null, DEBUG, format, args);
  }

  public static void debug(String fqcn, Logger logger, Marker marker,
      String msg) {
    maybeLog(fqcn, logger, marker, DEBUG, msg, (Throwable) null);
  }

  public static void debug(String fqcn, Logger logger, Marker marker,
      String msg, Throwable t) {
    maybeLog(fqcn, logger, marker, DEBUG, msg, t);
  }

  public static void debug(String fqcn, Logger logger, Marker marker,
      String format, Object arg) {
    maybeLog(fqcn, logger, marker, DEBUG, format, arg);
  }

  public static void debug(String fqcn, Logger logger, Marker marker,
      String format, Object arg1, Object arg2) {
    maybeLog(fqcn, logger, marker, DEBUG, format, arg1, arg2);
  }

  public static void debug(String fqcn, Logger logger, Marker marker,
      String format, Object... args) {
    maybeLog(fqcn, logger, marker, DEBUG, format, args);
  }

  // INFO

  public static void info(String fqcn, Logger logger, String msg) {
    maybeLog(fqcn, logger, null, INFO, msg, (Throwable) null);
  }

  public static void info(String fqcn, Logger logger, String msg, Throwable t) {
    maybeLog(fqcn, logger, null, INFO, msg, t);
  }

  public static void info(String fqcn, Logger logger, String format,
      Object arg) {
    maybeLog(fqcn, logger, null, INFO, format, arg);
  }

  public static void info(String fqcn, Logger logger, String format,
      Object arg1, Object arg2) {
    maybeLog(fqcn, logger, null, INFO, format, arg1, arg2);
  }

  public static void info(String fqcn, Logger logger, String format,
      Object... args) {
    maybeLog(fqcn, logger, null, INFO, format, args);
  }

  public static void info(String fqcn, Logger logger, Marker marker,
      String msg) {
    maybeLog(fqcn, logger, marker, INFO, msg, (Throwable) null);
  }

  public static void info(String fqcn, Logger logger, Marker marker,
      String msg, Throwable t) {
    maybeLog(fqcn, logger, marker, INFO, msg, t);
  }

  public static void info(String fqcn, Logger logger, Marker marker,
      String format, Object arg) {
    maybeLog(fqcn, logger, marker, INFO, format, arg);
  }

  public static void info(String fqcn, Logger logger, Marker marker,
      String format, Object arg1, Object arg2) {
    maybeLog(fqcn, logger, marker, INFO, format, arg1, arg2);
  }

  public static void info(String fqcn, Logger logger, Marker marker,
      String format, Object... args) {
    maybeLog(fqcn, logger, marker, INFO, format, args);
  }

  // WARN

  public static void warn(String fqcn, Logger logger, String msg) {
    maybeLog(fqcn, logger, null, WARN, msg, (Throwable) null);
  }

  public static void warn(String fqcn, Logger logger, String msg, Throwable t) {
    maybeLog(fqcn, logger, null, WARN, msg, t);
  }

  public static void warn(String fqcn, Logger logger, String format,
      Object arg) {
    maybeLog(fqcn, logger, null, WARN, format, arg);
  }

  public static void warn(String fqcn, Logger logger, String format,
      Object arg1, Object arg2) {
    maybeLog(fqcn, logger, null, WARN, format, arg1, arg2);
  }

  public static void warn(String fqcn, Logger logger, String format,
      Object... args) {
    maybeLog(fqcn, logger, null, WARN, format, args);
  }

  public static void warn(String fqcn, Logger logger, Marker marker,
      String msg) {
    maybeLog(fqcn, logger, marker, WARN, msg, (Throwable) null);
  }

  public static void warn(String fqcn, Logger logger, Marker marker,
      String msg, Throwable t) {
    maybeLog(fqcn, logger, marker, WARN, msg, t);
  }

  public static void warn(String fqcn, Logger logger, Marker marker,
      String format, Object arg) {
    maybeLog(fqcn, logger, marker, WARN, format, arg);
  }

  public static void warn(String fqcn, Logger logger, Marker marker,
      String format, Object arg1, Object arg2) {
    maybeLog(fqcn, logger, marker, WARN, format, arg1, arg2);
  }

  public static void warn(String fqcn, Logger logger, Marker marker,
      String format, Object... args) {
    maybeLog(fqcn, logger, marker, WARN, format, args);
  }

  // ERROR

  public static void error(String fqcn, Logger logger, String msg) {
    maybeLog(fqcn, logger, null, ERROR, msg, (Throwable) null);
  }

  public static void error(String fqcn, Logger logger, String msg, Throwable t) {
    maybeLog(fqcn, logger, null, ERROR, msg, t);
  }

  public static void error(String fqcn, Logger logger, String format,
      Object arg) {
    maybeLog(fqcn, logger, null, ERROR, format, arg);
  }

  public static void error(String fqcn, Logger logger, String format,
      Object arg1, Object arg2) {
    maybeLog(fqcn, logger, null, ERROR, format, arg1, arg2);
  }

  public static void error(String fqcn, Logger logger, String format,
      Object... args) {
    maybeLog(fqcn, logger, null, ERROR, format, args);
  }

  public static void error(String fqcn, Logger logger, Marker marker,
      String msg) {
    maybeLog(fqcn, logger, marker, ERROR, msg, (Throwable) null);
  }

  public static void error(String fqcn, Logger logger, Marker marker,
      String msg, Throwable t) {
    maybeLog(fqcn, logger, marker, ERROR, msg, t);
  }

  public static void error(String fqcn, Logger logger, Marker marker,
      String format, Object arg) {
    maybeLog(fqcn, logger, marker, ERROR, format, arg);
  }

  public static void error(String fqcn, Logger logger, Marker marker,
      String format, Object arg1, Object arg2) {
    maybeLog(fqcn, logger, marker, ERROR, format, arg1, arg2);
  }

  public static void error(String fqcn, Logger logger, Marker marker,
      String format, Object... args) {
    maybeLog(fqcn, logger, marker, ERROR, format, args);
  }

  // Private Methods

  private static void maybeLog(String fqcn, Logger logger, Marker marker,
      Level level, String message, Throwable t) {
    if (isEnabled(logger, marker, level)) {
      logger.log(fqcn, new SimpleEntry(marker, level, message, t));
    }
  }

  private static void maybeLog(String fqcn, Logger logger, Marker marker,
      Level level, String format, Object arg) {
    if (isEnabled(logger, marker, level)) {
      logger.log(fqcn, new StandardEntry(marker, level, format,
          new Object[] {arg}, logger.getFormatter()));
    }
  }

  private static void maybeLog(String fqcn, Logger logger, Marker marker,
      Level level, String format, Object arg1, Object arg2) {
    if (isEnabled(logger, marker, level)) {
      logger.log(fqcn, new StandardEntry(marker, level, format,
          new Object[] {arg1, arg2}, logger.getFormatter()));
    }
  }

  private static void maybeLog(String fqcn, Logger logger, Marker marker,
      Level level, String format, Object[] args) {
    if (isEnabled(logger, marker, level)) {
      logger.log(fqcn, new StandardEntry(marker, level, format,
          args, logger.getFormatter()));
    }
  }

  public static boolean isEnabled(Logger logger, Marker marker, Level level) {
    switch(level) {
      case TRACE:
        return logger.isTraceEnabled(marker);
      case DEBUG:
        return logger.isDebugEnabled(marker);
      case INFO:
        return logger.isInfoEnabled(marker);
      case WARN:
        return logger.isWarnEnabled(marker);
      case ERROR:
        return logger.isErrorEnabled(marker);
      default:
        // will only happen if a new level is defined
        throw new IllegalStateException("Level " + level
            + " is not recognized.");
    }
  }
}
