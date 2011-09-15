package org.slf4j.helpers;

import org.slf4j.Level;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.entries.Entry;
import org.slf4j.entries.MarkerAwareEntry;
import org.slf4j.entries.ParameterAwareEntry;
import org.slf4j.entries.ThrowableAwareEntry;
import org.slf4j.spi.LocationAwareLogger;
import org.slf4j.spi.LoggerAdapter;

public class LegacyLoggerWrapper implements LoggerAdapter {

  private Logger logger;
  private LocationAwareLogger lal = null;
  private boolean isLAL = false;
 
  public LegacyLoggerWrapper(Logger logger) {
    this.logger = logger;
    if (logger instanceof LocationAwareLogger) {
      this.lal = (LocationAwareLogger) logger;
      this.isLAL = true;
    }
  }

  public String getNameInternal() {
    return logger.getName();
  }

  public boolean isEnabledInternal(Marker marker, Level level) {
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
        throw new IllegalStateException("Level " + level
            + " is not recognized.");
    }
  }

  public void logInternal(String fqcn, Entry entry) {
    Throwable throwable = null;
    if (entry instanceof ThrowableAwareEntry) {
      throwable = ((ThrowableAwareEntry)entry).getThrowable();
    }
    Marker marker = null;
    if (entry instanceof MarkerAwareEntry) {
      marker = ((MarkerAwareEntry)entry).getMarker();
    }
    if (isLAL) {
      Object[] params = null;
      if (entry instanceof ParameterAwareEntry) {
        params = ((ParameterAwareEntry)entry).getParameters();
      }
      lal.log(marker, fqcn, entry.getLevel().intValue(), entry.getMessage(),
          params, throwable);
    } else {
      switch(entry.getLevel()) {
        case TRACE:
          logger.trace(marker, entry.getMessage(), throwable);
          break;
        case DEBUG:
          logger.debug(marker, entry.getMessage(), throwable);
          break;
        case INFO:
          logger.info(marker, entry.getMessage(), throwable);
          break;
        case WARN:
          logger.warn(marker, entry.getMessage(), throwable);
          break;
        case ERROR:
          logger.error(marker, entry.getMessage(), throwable);
          break;
        default:
          throw new IllegalStateException("Level " + entry.getLevel()
              + " is not recognized.");
      }
    }
  }
}
