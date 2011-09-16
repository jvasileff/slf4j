package org.slf4j.ext;

import org.slf4j.Formatter;
import org.slf4j.Level;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.entries.Entry;
import org.slf4j.helpers.AbstractLogger;
import org.slf4j.helpers.LegacyLoggerWrapper;
import org.slf4j.spi.LocationAwareLogger;
import org.slf4j.spi.LoggerAdapter;

/**
 * A helper class wrapping an {@link org.slf4j.Logger} instance preserving
 * location information if the wrapped instance supports it.
 *
 * @author Ralph Goers
 * @author Ceki G&uuml;lc&uuml;
 * @author John Vasileff
 */
public class LoggerWrapper extends AbstractLogger {

  // FIXME: serialization/readResolve
  private static final long serialVersionUID = 8920070855870975205L;

  // To ensure consistency between two instances sharing the same name
  // (homonyms)
  // a LoggerWrapper should not contain any state beyond
  // the Logger instance it wraps.
  // fqcn depend on the caller, but its value would not be different
  // between successive invocations of a factory class

  @Deprecated // is direct access to this var necessary?
  protected final Logger logger;

  final String fqcn;

  // is this logger instance a LocationAwareLogger
  @Deprecated
  protected final boolean instanceofLAL;

  private final LoggerAdapter adapter;

  public LoggerWrapper(Logger logger, String fqcn) {
    this.logger = logger;
    this.fqcn = fqcn;

    // who knows how this class is used, logger may be slf4j 1.6.x.
    if (logger instanceof LoggerAdapter) {
      this.adapter = (LoggerAdapter) logger;
    } else {
      this.adapter = new LegacyLoggerWrapper(logger);
    }

    // for compatibility, we can't remove "protected ... instanceofLAL"
    if (logger instanceof LocationAwareLogger) {
      instanceofLAL = true;
    } else {
      instanceofLAL = false;
    }
  }

  public LoggerWrapper withFormatter(Formatter formatter) {
    // TODO: javadoc should recommend subclasses override this method returning
    //    subclass rather than LoggerWrapper
    return new LoggerWrapper(logger.withFormatter(formatter),
        LoggerWrapper.class.getName());
  }

  public String getNameInternal() {
    return adapter.getNameInternal();
  }

  public boolean isEnabledInternal(Marker marker, Level level) {
    return adapter.isEnabledInternal(marker, level);
  }

  public void logInternal(String fqcn, Entry entry) {
    adapter.logInternal(fqcn, entry);
  }

  public Logger getWrappedLogger() {
    return logger;
  }
}
