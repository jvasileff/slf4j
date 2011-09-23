package org.slf4j.helpers;

import static org.slf4j.Level.DEBUG;
import static org.slf4j.Level.ERROR;
import static org.slf4j.Level.INFO;
import static org.slf4j.Level.TRACE;
import static org.slf4j.Level.WARN;

import java.io.ObjectStreamException;
import java.io.Serializable;

import org.slf4j.Level;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.messages.Message;
import org.slf4j.spi.LocationAwareLogger;
import org.slf4j.spi.LoggerAdapter;

public abstract class AbstractLogger implements LoggerAdapter,
    LocationAwareLogger, Serializable {

  private static final long serialVersionUID = 3237785459601004498L;

  private static final String FQCN = AbstractLogger.class.getName();

  public final String getName() {
    return getNameInternal();
  }

  public final void log(Marker marker, Level level, Message entry) {
    logInternal(FQCN, marker, level, entry);
  }

  public final void log(String callerFQCN, Marker marker, Level level, Message entry) {
    logInternal(callerFQCN, marker, level, entry);
  }

  // TRACE

  public final boolean isTraceEnabled() {
    return isEnabledInternal(null, TRACE, null);
  }

  public final boolean isTraceEnabled(Marker marker) {
    return isEnabledInternal(marker, TRACE, null);
  }

  public final void trace(String msg) {
    maybeLog(null, TRACE, msg, (Throwable) null);
  }

  public final void trace(String msg, Throwable t) {
    maybeLog(null, TRACE, msg, t);
  }

  public final void trace(String format, Object arg) {
    maybeLog(null, TRACE, format, arg);
  }

  public final void trace(String format, Object arg1, Object arg2) {
    maybeLog(null, TRACE, format, arg1, arg2);
  }

  public final void trace(String format, Object... args) {
    maybeLog(null, TRACE, format, args);
  }

  public final void trace(Marker marker, String msg) {
    maybeLog(marker, TRACE, msg, (Throwable) null);
  }

  public final void trace(Marker marker, String msg, Throwable t) {
    maybeLog(marker, TRACE, msg, t);
  }

  public final void trace(Marker marker, String format, Object arg) {
    maybeLog(marker, TRACE, format, arg);
  }

  public final void trace(Marker marker, String format, Object arg1,
      Object arg2) {
    maybeLog(marker, TRACE, format, arg1, arg2);
  }

  public final void trace(Marker marker, String format, Object... args) {
    maybeLog(marker, TRACE, format, args);
  }

  // DEBUG

  public final boolean isDebugEnabled() {
    return isEnabledInternal(null, DEBUG, null);
  }

  public final boolean isDebugEnabled(Marker marker) {
    return isEnabledInternal(marker, DEBUG, null);
  }

  public final void debug(String msg) {
    maybeLog(null, DEBUG, msg, (Throwable) null);
  }

  public final void debug(String msg, Throwable t) {
    maybeLog(null, DEBUG, msg, t);
  }

  public final void debug(String format, Object arg) {
    maybeLog(null, DEBUG, format, arg);
  }

  public final void debug(String format, Object arg1, Object arg2) {
    maybeLog(null, DEBUG, format, arg1, arg2);
  }

  public final void debug(String format, Object... args) {
    maybeLog(null, DEBUG, format, args);
  }

  public final void debug(Marker marker, String msg) {
    maybeLog(marker, DEBUG, msg, (Throwable) null);
  }

  public final void debug(Marker marker, String msg, Throwable t) {
    maybeLog(marker, DEBUG, msg, t);
  }

  public final void debug(Marker marker, String format, Object arg) {
    maybeLog(marker, DEBUG, format, arg);
  }

  public final void debug(Marker marker, String format, Object arg1,
      Object arg2) {
    maybeLog(marker, DEBUG, format, arg1, arg2);
  }

  public final void debug(Marker marker, String format, Object... args) {
    maybeLog(marker, DEBUG, format, args);
  }

  // INFO

  public final boolean isInfoEnabled() {
    return isEnabledInternal(null, INFO, null);
  }

  public final boolean isInfoEnabled(Marker marker) {
    return isEnabledInternal(marker, INFO, null);
  }

  public final void info(String msg) {
    maybeLog(null, INFO, msg, (Throwable) null);
  }

  public final void info(String msg, Throwable t) {
    maybeLog(null, INFO, msg, t);
  }

  public final void info(String format, Object arg) {
    maybeLog(null, INFO, format, arg);
  }

  public final void info(String format, Object arg1, Object arg2) {
    maybeLog(null, INFO, format, arg1, arg2);
  }

  public final void info(String format, Object... args) {
    maybeLog(null, INFO, format, args);
  }

  public final void info(Marker marker, String msg) {
    maybeLog(marker, INFO, msg, (Throwable) null);
  }

  public final void info(Marker marker, String msg, Throwable t) {
    maybeLog(marker, INFO, msg, t);
  }

  public final void info(Marker marker, String format, Object arg) {
    maybeLog(marker, INFO, format, arg);
  }

  public final void info(Marker marker, String format, Object arg1,
      Object arg2) {
    maybeLog(marker, INFO, format, arg1, arg2);
  }

  public final void info(Marker marker, String format, Object... args) {
    maybeLog(marker, INFO, format, args);
  }

  // WARN

  public final boolean isWarnEnabled() {
    return isEnabledInternal(null, WARN, null);
  }

  public final boolean isWarnEnabled(Marker marker) {
    return isEnabledInternal(marker, WARN, null);
  }

  public final void warn(String msg) {
    maybeLog(null, WARN, msg, (Throwable) null);
  }

  public final void warn(String msg, Throwable t) {
    maybeLog(null, WARN, msg, t);
  }

  public final void warn(String format, Object arg) {
    maybeLog(null, WARN, format, arg);
  }

  public final void warn(String format, Object arg1, Object arg2) {
    maybeLog(null, WARN, format, arg1, arg2);
  }

  public final void warn(String format, Object... args) {
    maybeLog(null, WARN, format, args);
  }

  public final void warn(Marker marker, String msg) {
    maybeLog(marker, WARN, msg, (Throwable) null);
  }

  public final void warn(Marker marker, String msg, Throwable t) {
    maybeLog(marker, WARN, msg, t);
  }

  public final void warn(Marker marker, String format, Object arg) {
    maybeLog(marker, WARN, format, arg);
  }

  public final void warn(Marker marker, String format, Object arg1,
      Object arg2) {
    maybeLog(marker, WARN, format, arg1, arg2);
  }

  public final void warn(Marker marker, String format, Object... args) {
    maybeLog(marker, WARN, format, args);
  }

  // ERROR

  public final boolean isErrorEnabled() {
    return isEnabledInternal(null, ERROR, null);
  }

  public final boolean isErrorEnabled(Marker marker) {
    return isEnabledInternal(marker, ERROR, null);
  }

  public final void error(String msg) {
    maybeLog(null, ERROR, msg, (Throwable) null);
  }

  public final void error(String msg, Throwable t) {
    maybeLog(null, ERROR, msg, t);
  }

  public final void error(String format, Object arg) {
    maybeLog(null, ERROR, format, arg);
  }

  public final void error(String format, Object arg1, Object arg2) {
    maybeLog(null, ERROR, format, arg1, arg2);
  }

  public final void error(String format, Object... args) {
    maybeLog(null, ERROR, format, args);
  }

  public final void error(Marker marker, String msg) {
    maybeLog(marker, ERROR, msg, (Throwable) null);
  }

  public final void error(Marker marker, String msg, Throwable t) {
    maybeLog(marker, ERROR, msg, t);
  }

  public final void error(Marker marker, String format, Object arg) {
    maybeLog(marker, ERROR, format, arg);
  }

  public final void error(Marker marker, String format, Object arg1,
      Object arg2) {
    maybeLog(marker, ERROR, format, arg1, arg2);
  }

  public final void error(Marker marker, String format, Object... args) {
    maybeLog(marker, ERROR, format, args);
  }

  // Private Methods

  private void maybeLog(Marker marker, Level level, String message,
      Throwable t) {
    if (isEnabledInternal(marker, level, null)) {
      logInternal(FQCN, marker, level,
          newMessage(message, null, t));
    }
  }

  private void maybeLog(Marker marker, Level level,
      String format, Object arg) {
    if (isEnabledInternal(marker, level, null)) {
      logInternal(FQCN, marker, level,
          newMessage(format, new Object[] {arg}));
    }
  }

  private void maybeLog(Marker marker, Level level,
      String format, Object arg1, Object arg2) {
    if (isEnabledInternal(marker, level, null)) {
      logInternal(FQCN, marker, level,
          newMessage(format, new Object[] {arg1, arg2}));
    }
  }

  private void maybeLog(Marker marker, Level level,
      String format, Object[] args) {
    if (isEnabledInternal(marker, level, null)) {
      logInternal(FQCN, marker, level,
          newMessage(format, args));
    }
  }

  public final void log(Marker marker, String fqcn, int level,
      String formattedMessage, Object[] argArray, Throwable t) {
    logInternal(fqcn, marker, Level.valueOfLevel(level),
        newMessage(formattedMessage, argArray, t));
  }

  /**
   * Construct a new Message for the provided parameters.
   * 
   * @param format The unformatted message
   * @param args The arguments, or null.
   * @return The Message;
   */
  protected Message newMessage(String format, Object... args) {
    if (args == null || args.length == 0) {
      return new SimpleMessage(format);
    } else {
      return new StandardMessage(format, args);
    }
  }

  /**
   * Construct a new Message for the provided parameters.
   * 
   * @param formattedMessage The message which has already been formatted.
   * @param args The arguments, or null.
   * @param throwable The Throwable or null.
   * @return The Message;
   */
  protected Message newMessage(String formattedMessage, Object[] args,
      Throwable throwable) {
    return new SimpleMessage(formattedMessage, args, throwable);
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
}