package org.slf4j.helpers;

import org.slf4j.Level;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.messages.Message;
import org.slf4j.messages.ParameterizedMessage;
import org.slf4j.messages.ThrowableMessage;
import org.slf4j.spi.LocationAwareLogger;

public class LegacyLoggerWrapper extends AbstractLogger {

  private Logger logger;
  private LocationAwareLogger lal = null;
  private boolean isLAL = false;
 
  public LegacyLoggerWrapper(Logger logger) {
    init(logger);
  }

  private void init(Logger logger) {
    this.logger = logger;
    if (logger instanceof LocationAwareLogger) {
      this.lal = (LocationAwareLogger) logger;
      this.isLAL = true;
    }
  }

  public String getNameInternal() {
    return logger.getName();
  }

  public boolean isEnabledInternal(Marker marker, Level level, Message message) {
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

  public void logInternal(String fqcn, Marker marker, Level level, Message entry) {
    Throwable throwable = null;
    if (entry instanceof ThrowableMessage) {
      throwable = ((ThrowableMessage)entry).getThrowable();
    }
    if (isLAL) {
      Object[] params = null;
      if (entry instanceof ParameterizedMessage) {
        params = ((ParameterizedMessage)entry).getParameters();
      }
      lal.log(marker, fqcn, level.intValue(), entry.getFormattedMessage(),
          params, throwable);
    } else {
      switch(level) {
        case TRACE:
          logger.trace(marker, entry.getFormattedMessage(), throwable);
          break;
        case DEBUG:
          logger.debug(marker, entry.getFormattedMessage(), throwable);
          break;
        case INFO:
          logger.info(marker, entry.getFormattedMessage(), throwable);
          break;
        case WARN:
          logger.warn(marker, entry.getFormattedMessage(), throwable);
          break;
        case ERROR:
          logger.error(marker, entry.getFormattedMessage(), throwable);
          break;
        default:
          throw new IllegalStateException("Level " + level
              + " is not recognized.");
      }
    }
  }
}
