package org.slf4j.helpers;

import static org.slf4j.helpers.Level.DEBUG;
import static org.slf4j.helpers.Level.ERROR;
import static org.slf4j.helpers.Level.INFO;
import static org.slf4j.helpers.Level.TRACE;
import static org.slf4j.helpers.Level.WARN;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

/**
 * Serves as base class for named logger implementation. More significantly, this
 * class establishes deserialization behavior. See @see #readResolve. 
 * 
 * @author Ceki Gulcu
 * @since 1.5.3
 */
@Deprecated
abstract class NamedLoggerBase implements Logger, Serializable {

  private static final long serialVersionUID = 7535258609338176893L;

  protected String name;
  
  public String getName() {
    return name;
  }
  
  /**
   * Replace this instance with a homonymous (same name) logger returned 
   * by LoggerFactory. Note that this method is only called during 
   * deserialization.
   * 
   * <p>
   * This approach will work well if the desired ILoggerFactory is the one
   * references by LoggerFactory. However, if the user manages its logger hierarchy
   * through a different (non-static) mechanism, e.g. dependency injection, then
   * this approach would be mostly counterproductive.
   * 
   * @return logger with same name as returned by LoggerFactory
   * @throws ObjectStreamException
   */
  protected Object readResolve() throws ObjectStreamException {
    // using getName() instead of this.name works even for
    // NOPLogger
    return LoggerFactory.getLogger(getName());
  }

  // TRACE

  public final void trace(Map supplementalData) {
    trace((Marker) null, supplementalData);
  }

  public final void trace(Map supplementalData, String msg) {
    trace((Marker) null, supplementalData, msg);
  }

  public final void trace(Map supplementalData, String format, Object... args) {
    trace(null, supplementalData, format, args);
  }

  public final void trace(Marker marker, Map supplementalData) {
    maybeLog(marker, TRACE, supplementalData);
  }

  public final void trace(Marker marker, Map supplementalData, String msg) {
    log(marker, TRACE, msg);
  }

  public final void trace(Marker marker, Map supplementalData, String format,
      Object... args) {
    log(marker, TRACE, format, args);
  }

  // DEBUG

  public final void debug(Map supplementalData) {
    debug((Marker) null, supplementalData);
  }

  public final void debug(Map supplementalData, String msg) {
    debug((Marker) null, supplementalData, msg);
  }

  public final void debug(Map supplementalData, String format, Object... args) {
    debug(null, supplementalData, format, args);
  }

  public final void debug(Marker marker, Map supplementalData) {
    maybeLog(marker, DEBUG, supplementalData);
  }

  public final void debug(Marker marker, Map supplementalData, String msg) {
    log(marker, DEBUG, msg);
  }

  public final void debug(Marker marker, Map supplementalData, String format,
      Object... args) {
    log(marker, DEBUG, format, args);
  }

  // INFO

  public final void info(Map supplementalData) {
    info((Marker) null, supplementalData);
  }

  public final void info(Map supplementalData, String msg) {
    info((Marker) null, supplementalData, msg);
  }

  public final void info(Map supplementalData, String format, Object... args) {
    info(null, supplementalData, format, args);
  }

  public final void info(Marker marker, Map supplementalData) {
    maybeLog(marker, INFO, supplementalData);
  }

  public final void info(Marker marker, Map supplementalData, String msg) {
    log(marker, INFO, msg);
  }

  public final void info(Marker marker, Map supplementalData, String format,
      Object... args) {
    log(marker, INFO, format, args);
  }

  // WARN

  public final void warn(Map supplementalData) {
    warn((Marker) null, supplementalData);
  }

  public final void warn(Map supplementalData, String msg) {
    warn((Marker) null, supplementalData, msg);
  }

  public final void warn(Map supplementalData, String format, Object... args) {
    warn(null, supplementalData, format, args);
  }

  public final void warn(Marker marker, Map supplementalData) {
    maybeLog(marker, WARN, supplementalData);
  }

  public final void warn(Marker marker, Map supplementalData, String msg) {
    log(marker, WARN, msg);
  }

  public final void warn(Marker marker, Map supplementalData, String format,
      Object... args) {
    log(marker, WARN, format, args);
  }

  // ERROR

  public final void error(Map supplementalData) {
    error((Marker) null, supplementalData);
  }

  public final void error(Map supplementalData, String msg) {
    error((Marker) null, supplementalData, msg);
  }

  public final void error(Map supplementalData, String format, Object... args) {
    error(null, supplementalData, format, args);
  }

  public final void error(Marker marker, Map supplementalData) {
    maybeLog(marker, ERROR, supplementalData);
  }

  public final void error(Marker marker, Map supplementalData, String msg) {
    log(marker, ERROR, msg);
  }

  public final void error(Marker marker, Map supplementalData, String format,
      Object... args) {
    log(marker, ERROR, format, args);
  } 

  // Private Methods

  private boolean isEnabled(Level level) {
    switch(level) {
      case TRACE :
        return isTraceEnabled();
      case DEBUG :
        return isDebugEnabled();
      case INFO :
        return isInfoEnabled();
      case WARN :
        return isWarnEnabled();
      case ERROR :
        return isErrorEnabled();
      default:
        // will only happen if a new level is defined
        throw new IllegalStateException("Level " + level
            + " is not recognized.");
    }
  }

  private void log(Marker marker, Level level, String msg) {
    switch(level) {
      case TRACE :
        trace(marker, msg);
        break;
      case DEBUG :
        debug(marker, msg);
        break;
      case INFO :
        info(marker, msg);
        break;
      case WARN :
        warn(marker, msg);
        break;
      case ERROR :
        error(marker, msg);
        break;
      default:
        // will only happen if a new level is defined
        throw new IllegalStateException("Level " + level
            + " is not recognized.");
    }
  }

  private void log(Marker marker, Level level, String format, Object[] args) {
    switch(level) {
      case TRACE :
        trace(marker, format, args);
        break;
      case DEBUG :
        debug(marker, format, args);
        break;
      case INFO :
        info(marker, format, args);
        break;
      case WARN :
        warn(marker, format, args);
        break;
      case ERROR :
        error(marker, format, args);
        break;
      default:
        // will only happen if a new level is defined
        throw new IllegalStateException("Level " + level
            + " is not recognized.");
    }
  }

  private void maybeLog(Marker marker, Level level, Map supplementalData) {
    if (isEnabled(level)) {
      log(marker, level,
          supplementalData == null ? null : supplementalData.toString());
    }
  }
}
