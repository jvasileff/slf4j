package org.slf4j.helpers;

import org.slf4j.Level;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.messages.DefaultMessage;
import org.slf4j.messages.SimpleMessage;

public class LoggerUtils {

  private LoggerUtils() {
    // no instances;
  }

  public static void logObject(String fqcn, Logger logger, Marker marker,
      Level level, Object message) {
    logObject(fqcn, logger, marker, level, message, (Throwable) null);
  }

  public static void logObject(String fqcn, Logger logger, Marker marker,
      Level level, Object message, Throwable t) {
    if (isEnabled(logger, marker, level)) {
      String msg = String.valueOf(message);
      logger.log(fqcn, marker, level, new SimpleMessage(msg, t));
    }
  }

  public static void log(String fqcn, Logger logger, Marker marker,
      Level level, String message) {
    logObject(fqcn, logger, marker, level, message, (Throwable) null);
  }

  public static void log(String fqcn, Logger logger, Marker marker,
      Level level, String message, Throwable t) {
    if (isEnabled(logger, marker, level)) {
      logger.log(fqcn, marker, level, new SimpleMessage(message, t));
    }
  }

  public static void log(String fqcn, Logger logger, Marker marker,
      Level level, String format, Object... args) {
    if (isEnabled(logger, marker, level)) {
      logger.log(fqcn, marker, level, new DefaultMessage(format, args));
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
        throw new IllegalArgumentException("Level " + level
            + " is not recognized.");
    }
  }
}
