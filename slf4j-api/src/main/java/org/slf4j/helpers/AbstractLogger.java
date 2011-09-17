package org.slf4j.helpers;

import static org.slf4j.Level.DEBUG;
import static org.slf4j.Level.ERROR;
import static org.slf4j.Level.INFO;
import static org.slf4j.Level.TRACE;
import static org.slf4j.Level.WARN;

import java.io.ObjectStreamException;
import java.io.Serializable;

import org.slf4j.Formatter;
import org.slf4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.formatters.StandardFormatter;
import org.slf4j.messages.Message;
import org.slf4j.spi.LocationAwareLogger;
import org.slf4j.spi.LoggerAdapter;

public abstract class AbstractLogger implements LoggerAdapter,
    LocationAwareLogger, Serializable {

  private static final long serialVersionUID = 3237785459601004498L;

  private static final String FQCN = AbstractLogger.class.getName();

  private final Formatter formatter;

  public AbstractLogger() {
    this(StandardFormatter.getInstance());
  }

  public AbstractLogger(Formatter formatter) {
    this.formatter = formatter;
  }

  public final String getName() {
    return getNameInternal();
  }

  public final void log(Message entry) {
    logInternal(FQCN, entry);
  }

  public final void log(String callerFQCN, Message entry) {
    logInternal(callerFQCN, entry);
  }

  // TRACE

  public final boolean isTraceEnabled() {
    return isEnabledInternal(null, TRACE);
  }

  public final boolean isTraceEnabled(Marker marker) {
    return isEnabledInternal(marker, TRACE);
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
    return isEnabledInternal(null, DEBUG);
  }

  public final boolean isDebugEnabled(Marker marker) {
    return isEnabledInternal(marker, DEBUG);
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
    return isEnabledInternal(null, INFO);
  }

  public final boolean isInfoEnabled(Marker marker) {
    return isEnabledInternal(marker, INFO);
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
    return isEnabledInternal(null, WARN);
  }

  public final boolean isWarnEnabled(Marker marker) {
    return isEnabledInternal(marker, WARN);
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
    return isEnabledInternal(null, ERROR);
  }

  public final boolean isErrorEnabled(Marker marker) {
    return isEnabledInternal(marker, ERROR);
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
    if (isEnabledInternal(marker, level)) {
      logInternal(FQCN, new SimpleMessage(marker, level, message, t));
    }
  }

  private void maybeLog(Marker marker, Level level,
      String format, Object arg) {
    if (isEnabledInternal(marker, level)) {
      logInternal(FQCN, new StandardMessage(marker, level, format,
          new Object[] {arg}, formatter));
    }
  }

  private void maybeLog(Marker marker, Level level,
      String format, Object arg1, Object arg2) {
    if (isEnabledInternal(marker, level)) {
      logInternal(FQCN, new StandardMessage(marker, level, format,
          new Object[] {arg1, arg2}, formatter));
    }
  }

  private void maybeLog(Marker marker, Level level,
      String format, Object[] args) {
    if (isEnabledInternal(marker, level)) {
      logInternal(FQCN, new StandardMessage(marker, level, format,
          args, formatter));
    }
  }

  public Logger withFormatter(Formatter formatter) {
    return new LoggerImpl(this, formatter);
  }

  public final Formatter getFormatter() {
    return formatter;
  }

  public final void log(Marker marker, String fqcn, int level, String message,
      Object[] argArray, Throwable t) {
    logInternal(fqcn, new SimpleMessage(
        marker, Level.valueOfLevel(level), message, argArray, t));
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

    // FIXME: restore formatter
    //return LoggerFactory.getLogger(getName()).withFormatter(formatter);
  }
}