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

public abstract class AbstractLogger implements Logger, Serializable {

  private static final long serialVersionUID = 3237785459601004498L;

  private String name;

  protected abstract boolean isEnabled(Marker marker, Level level);
  protected abstract void log(Marker marker, Level level, Map supplementalData,
      String msg, Throwable t);

  protected AbstractLogger(String name) {
    setName(name);
  }

  public final String getName() {
    return name;
  }

  protected final void setName(String name) {
    this.name = name;
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

  public final boolean isTraceEnabled() {
    return isEnabled(null, TRACE);
  }

  public final boolean isTraceEnabled(Marker marker) {
    return isEnabled(marker, TRACE);
  }

  public final void trace(String msg) {
    log(null, TRACE, null, msg, null);
  }

  public final void trace(String msg, Throwable t) {
    log(null, TRACE, null, msg, t);
  }

  public final void trace(String format, Object arg) {
    maybeLog(null, TRACE, null, format, arg);
  }

  public final void trace(String format, Object arg1, Object arg2) {
    maybeLog(null, TRACE, null, format, arg1, arg2);
  }

  public final void trace(String format, Object... args) {
    maybeLog(null, TRACE, null, format, args);
  }

  public final void trace(Map supplementalData) {
    maybeLog(null, TRACE, supplementalData);
  }

  public final void trace(Map supplementalData, String msg) {
    log(null, TRACE, supplementalData, msg, null);
  }

  public final void trace(Map supplementalData, String format, Object... argArray) {
    maybeLog(null, TRACE, supplementalData, format, argArray);
  }

  public final void trace(Marker marker, String msg) {
    log(marker, TRACE, null, msg, null);
  }

  public final void trace(Marker marker, String msg, Throwable t) {
    log(marker, TRACE, null, msg, t);
  }

  public final void trace(Marker marker, String format, Object arg) {
    maybeLog(marker, TRACE, null, format, arg);
  }

  public final void trace(Marker marker, String format, Object arg1, Object arg2) {
    maybeLog(marker, TRACE, null, format, arg1, arg2);
  }

  public final void trace(Marker marker, String format, Object... args) {
    maybeLog(marker, TRACE, null, format, args);
  }

  public final void trace(Marker marker, Map supplementalData) {
    maybeLog(marker, TRACE, supplementalData);
  }

  public final void trace(Marker marker, Map supplementalData, String msg) {
    log(marker, TRACE, supplementalData, msg, null);
  }

  public final void trace(Marker marker, Map supplementalData, String format,Object... args) {
    maybeLog(marker, TRACE, supplementalData, format, args);
  }

  // DEBUG

  public final boolean isDebugEnabled() {
    return isEnabled(null, DEBUG);
  }

  public final boolean isDebugEnabled(Marker marker) {
    return isEnabled(marker, DEBUG);
  }

  public final void debug(String msg) {
    log(null, DEBUG, null, msg, null);
  }

  public final void debug(String msg, Throwable t) {
    log(null, DEBUG, null, msg, t);
  }

  public final void debug(String format, Object arg) {
    maybeLog(null, DEBUG, null, format, arg);
  }

  public final void debug(String format, Object arg1, Object arg2) {
    maybeLog(null, DEBUG, null, format, arg1, arg2);
  }

  public final void debug(String format, Object... args) {
    maybeLog(null, DEBUG, null, format, args);
  }

  public final void debug(Map supplementalData) {
    maybeLog(null, DEBUG, supplementalData);
  }

  public final void debug(Map supplementalData, String msg) {
    log(null, DEBUG, supplementalData, msg, null);
  }

  public final void debug(Map supplementalData, String format, Object... argArray) {
    maybeLog(null, DEBUG, supplementalData, format, argArray);
  }

  public final void debug(Marker marker, String msg) {
    log(marker, DEBUG, null, msg, null);
  }

  public final void debug(Marker marker, String msg, Throwable t) {
    log(marker, DEBUG, null, msg, t);
  }

  public final void debug(Marker marker, String format, Object arg) {
    maybeLog(marker, DEBUG, null, format, arg);
  }

  public final void debug(Marker marker, String format, Object arg1, Object arg2) {
    maybeLog(marker, DEBUG, null, format, arg1, arg2);
  }

  public final void debug(Marker marker, String format, Object... args) {
    maybeLog(marker, DEBUG, null, format, args);
  }

  public final void debug(Marker marker, Map supplementalData) {
    maybeLog(marker, DEBUG, supplementalData);
  }

  public final void debug(Marker marker, Map supplementalData, String msg) {
    log(marker, DEBUG, supplementalData, msg, null);
  }

  public final void debug(Marker marker, Map supplementalData, String format,Object... args) {
    maybeLog(marker, DEBUG, supplementalData, format, args);
  }

  // INFO

  public final boolean isInfoEnabled() {
    return isEnabled(null, INFO);
  }

  public final boolean isInfoEnabled(Marker marker) {
    return isEnabled(marker, INFO);
  }

  public final void info(String msg) {
    log(null, INFO, null, msg, null);
  }

  public final void info(String msg, Throwable t) {
    log(null, INFO, null, msg, t);
  }

  public final void info(String format, Object arg) {
    maybeLog(null, INFO, null, format, arg);
  }

  public final void info(String format, Object arg1, Object arg2) {
    maybeLog(null, INFO, null, format, arg1, arg2);
  }

  public final void info(String format, Object... args) {
    maybeLog(null, INFO, null, format, args);
  }

  public final void info(Map supplementalData) {
    maybeLog(null, INFO, supplementalData);
  }

  public final void info(Map supplementalData, String msg) {
    log(null, INFO, supplementalData, msg, null);
  }

  public final void info(Map supplementalData, String format, Object... argArray) {
    maybeLog(null, INFO, supplementalData, format, argArray);
  }

  public final void info(Marker marker, String msg) {
    log(marker, INFO, null, msg, null);
  }

  public final void info(Marker marker, String msg, Throwable t) {
    log(marker, INFO, null, msg, t);
  }

  public final void info(Marker marker, String format, Object arg) {
    maybeLog(marker, INFO, null, format, arg);
  }

  public final void info(Marker marker, String format, Object arg1, Object arg2) {
    maybeLog(marker, INFO, null, format, arg1, arg2);
  }

  public final void info(Marker marker, String format, Object... args) {
    maybeLog(marker, INFO, null, format, args);
  }

  public final void info(Marker marker, Map supplementalData) {
    maybeLog(marker, INFO, supplementalData);
  }

  public final void info(Marker marker, Map supplementalData, String msg) {
    log(marker, INFO, supplementalData, msg, null);
  }

  public final void info(Marker marker, Map supplementalData, String format,Object... args) {
    maybeLog(marker, INFO, supplementalData, format, args);
  }

  // WARN

  public final boolean isWarnEnabled() {
    return isEnabled(null, WARN);
  }

  public final boolean isWarnEnabled(Marker marker) {
    return isEnabled(marker, WARN);
  }

  public final void warn(String msg) {
    log(null, WARN, null, msg, null);
  }

  public final void warn(String msg, Throwable t) {
    log(null, WARN, null, msg, t);
  }

  public final void warn(String format, Object arg) {
    maybeLog(null, WARN, null, format, arg);
  }

  public final void warn(String format, Object arg1, Object arg2) {
    maybeLog(null, WARN, null, format, arg1, arg2);
  }

  public final void warn(String format, Object... args) {
    maybeLog(null, WARN, null, format, args);
  }

  public final void warn(Map supplementalData) {
    maybeLog(null, WARN, supplementalData);
  }

  public final void warn(Map supplementalData, String msg) {
    log(null, WARN, supplementalData, msg, null);
  }

  public final void warn(Map supplementalData, String format, Object... argArray) {
    maybeLog(null, WARN, supplementalData, format, argArray);
  }

  public final void warn(Marker marker, String msg) {
    log(marker, WARN, null, msg, null);
  }

  public final void warn(Marker marker, String msg, Throwable t) {
    log(marker, WARN, null, msg, t);
  }

  public final void warn(Marker marker, String format, Object arg) {
    maybeLog(marker, WARN, null, format, arg);
  }

  public final void warn(Marker marker, String format, Object arg1, Object arg2) {
    maybeLog(marker, WARN, null, format, arg1, arg2);
  }

  public final void warn(Marker marker, String format, Object... args) {
    maybeLog(marker, WARN, null, format, args);
  }

  public final void warn(Marker marker, Map supplementalData) {
    maybeLog(marker, WARN, supplementalData);
  }

  public final void warn(Marker marker, Map supplementalData, String msg) {
    log(marker, WARN, supplementalData, msg, null);
  }

  public final void warn(Marker marker, Map supplementalData, String format,Object... args) {
    maybeLog(marker, WARN, supplementalData, format, args);
  }

  // ERROR

  public final boolean isErrorEnabled() {
    return isEnabled(null, ERROR);
  }

  public final boolean isErrorEnabled(Marker marker) {
    return isEnabled(marker, ERROR);
  }

  public final void error(String msg) {
    log(null, ERROR, null, msg, null);
  }

  public final void error(String msg, Throwable t) {
    log(null, ERROR, null, msg, t);
  }

  public final void error(String format, Object arg) {
    maybeLog(null, ERROR, null, format, arg);
  }

  public final void error(String format, Object arg1, Object arg2) {
    maybeLog(null, ERROR, null, format, arg1, arg2);
  }

  public final void error(String format, Object... args) {
    maybeLog(null, ERROR, null, format, args);
  }

  public final void error(Map supplementalData) {
    maybeLog(null, ERROR, supplementalData);
  }

  public final void error(Map supplementalData, String msg) {
    log(null, ERROR, supplementalData, msg, null);
  }

  public final void error(Map supplementalData, String format, Object... argArray) {
    maybeLog(null, ERROR, supplementalData, format, argArray);
  }

  public final void error(Marker marker, String msg) {
    log(marker, ERROR, null, msg, null);
  }

  public final void error(Marker marker, String msg, Throwable t) {
    log(marker, ERROR, null, msg, t);
  }

  public final void error(Marker marker, String format, Object arg) {
    maybeLog(marker, ERROR, null, format, arg);
  }

  public final void error(Marker marker, String format, Object arg1, Object arg2) {
    maybeLog(marker, ERROR, null, format, arg1, arg2);
  }

  public final void error(Marker marker, String format, Object... args) {
    maybeLog(marker, ERROR, null, format, args);
  }

  public final void error(Marker marker, Map supplementalData) {
    maybeLog(marker, ERROR, supplementalData);
  }

  public final void error(Marker marker, Map supplementalData, String msg) {
    log(marker, ERROR, supplementalData, msg, null);
  }

  public final void error(Marker marker, Map supplementalData, String format,Object... args) {
    maybeLog(marker, ERROR, supplementalData, format, args);
  }

  // Private Methods

  private void maybeLog(Marker marker, Level level, Map supplementalData, String format, Object arg) {
    if (isEnabled(level)) {
      FormattingTuple ft = MessageFormatter.format(format, arg);
      log(marker, level, supplementalData, ft.getMessage(), ft.getThrowable());
    }
  }

  private void maybeLog(Marker marker, Level level, Map supplementalData, String format, Object arg1,
      Object arg2) {
    if (isEnabled(level)) {
      FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
      log(marker, level, supplementalData, ft.getMessage(), ft.getThrowable());
    }
  }

  private void maybeLog(Marker marker, Level level, Map supplementalData, String format,
      Object[] argArray) {
    if (isEnabled(level)) {
      FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
      log(marker, level, supplementalData, ft.getMessage(), ft.getThrowable());
    }
  }

  private void maybeLog(Marker marker, Level level, Map supplementalData) {
    if (isEnabled(level)) {
      log(marker, level, supplementalData,
        supplementalData == null ? null : supplementalData.toString(), null);
    }
  }

  private boolean isEnabled(Level level) {
    return isEnabled(null, level);
  }
}
