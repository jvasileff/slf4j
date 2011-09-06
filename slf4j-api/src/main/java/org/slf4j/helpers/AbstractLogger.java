package org.slf4j.helpers;

import static org.slf4j.helpers.Level.DEBUG;
import static org.slf4j.helpers.Level.ERROR;
import static org.slf4j.helpers.Level.INFO;
import static org.slf4j.helpers.Level.TRACE;
import static org.slf4j.helpers.Level.WARN;

import java.io.ObjectStreamException;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

public abstract class AbstractLogger implements Logger, Serializable {

  private static final long serialVersionUID = 3237785459601004498L;

  private String name;

  protected abstract boolean isEnabled(Marker marker, Level level);
  protected abstract void log(Marker marker, Level level, String msg, Throwable t);

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
    log(null, TRACE, msg, null);
  }

  public final void trace(String msg, Throwable t) {
    log(null, TRACE, msg, t);
  }

  public final void trace(String format, Object[] argArray) {
    maybeLog(null, TRACE, format, argArray);
  }

  public final void trace(String format, Object arg) {
    maybeLog(null, TRACE, format, arg);
  }

  public final void trace(String format, Object arg1, Object arg2) {
    maybeLog(null, TRACE, format, arg1, arg2);
  }

  public final void trace(String format, Object arg1, Object arg2, Object arg3) {
    maybeLog(null, TRACE, format, arg1, arg2, arg3);
  }

  public final void trace(String format, Object arg1, Object arg2, Object arg3, Object arg4) {
    maybeLog(null, TRACE, format, arg1, arg2, arg3, arg4);
  }

  public final void trace(String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5) {
    maybeLog(null, TRACE, format, arg1, arg2, arg3, arg4, arg5);
  }

  public final void trace(String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6) {
    maybeLog(null, TRACE, format, arg1, arg2, arg3, arg4, arg5, arg6);
  }

  public final void trace(String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object... others) {
    maybeLog(null, TRACE, format, arg1, arg2, arg3, arg4, arg5, arg6, others);
  }

  public final void trace(Marker marker, String msg) {
    log(marker, TRACE, msg, null);
  }

  public final void trace(Marker marker, String msg, Throwable t) {
    log(marker, TRACE, msg, t);
  }

  public final void trace(Marker marker, String format, Object[] argArray) {
    maybeLog(marker, TRACE, format, argArray);
  }

  public final void trace(Marker marker, String format, Object arg) {
    maybeLog(marker, TRACE, format, arg);
  }

  public final void trace(Marker marker, String format, Object arg1, Object arg2) {
    maybeLog(marker, TRACE, format, arg1, arg2);
  }

  public final void trace(Marker marker, String format, Object arg1, Object arg2, Object arg3) {
    maybeLog(marker, TRACE, format, arg1, arg2, arg3);
  }

  public final void trace(Marker marker, String format, Object arg1, Object arg2, Object arg3, Object arg4) {
    maybeLog(marker, TRACE, format, arg1, arg2, arg3, arg4);
  }

  public final void trace(Marker marker, String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5) {
    maybeLog(marker, TRACE, format, arg1, arg2, arg3, arg4, arg5);
  }

  public final void trace(Marker marker, String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6) {
    maybeLog(marker, TRACE, format, arg1, arg2, arg3, arg4, arg5, arg6);
  }

  public final void trace(Marker marker, String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object... others) {
    maybeLog(marker, TRACE, format, arg1, arg2, arg3, arg4, arg5, arg6, others);
  }

  // DEBUG

  public final boolean isDebugEnabled() {
    return isEnabled(null, DEBUG);
  }

  public final boolean isDebugEnabled(Marker marker) {
    return isEnabled(marker, DEBUG);
  }

  public final void debug(String msg) {
    log(null, DEBUG, msg, null);
  }

  public final void debug(String msg, Throwable t) {
    log(null, DEBUG, msg, t);
  }

  public final void debug(String format, Object[] argArray) {
    maybeLog(null, DEBUG, format, argArray);
  }

  public final void debug(String format, Object arg) {
    maybeLog(null, DEBUG, format, arg);
  }

  public final void debug(String format, Object arg1, Object arg2) {
    maybeLog(null, DEBUG, format, arg1, arg2);
  }

  public final void debug(String format, Object arg1, Object arg2, Object arg3) {
    maybeLog(null, DEBUG, format, arg1, arg2, arg3);
  }

  public final void debug(String format, Object arg1, Object arg2, Object arg3, Object arg4) {
    maybeLog(null, DEBUG, format, arg1, arg2, arg3, arg4);
  }

  public final void debug(String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5) {
    maybeLog(null, DEBUG, format, arg1, arg2, arg3, arg4, arg5);
  }

  public final void debug(String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6) {
    maybeLog(null, DEBUG, format, arg1, arg2, arg3, arg4, arg5, arg6);
  }

  public final void debug(String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object... others) {
    maybeLog(null, DEBUG, format, arg1, arg2, arg3, arg4, arg5, arg6, others);
  }

  public final void debug(Marker marker, String msg) {
    log(marker, DEBUG, msg, null);
  }

  public final void debug(Marker marker, String msg, Throwable t) {
    log(marker, DEBUG, msg, t);
  }

  public final void debug(Marker marker, String format, Object[] argArray) {
    maybeLog(marker, DEBUG, format, argArray);
  }

  public final void debug(Marker marker, String format, Object arg) {
    maybeLog(marker, DEBUG, format, arg);
  }

  public final void debug(Marker marker, String format, Object arg1, Object arg2) {
    maybeLog(marker, DEBUG, format, arg1, arg2);
  }

  public final void debug(Marker marker, String format, Object arg1, Object arg2, Object arg3) {
    maybeLog(marker, DEBUG, format, arg1, arg2, arg3);
  }

  public final void debug(Marker marker, String format, Object arg1, Object arg2, Object arg3, Object arg4) {
    maybeLog(marker, DEBUG, format, arg1, arg2, arg3, arg4);
  }

  public final void debug(Marker marker, String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5) {
    maybeLog(marker, DEBUG, format, arg1, arg2, arg3, arg4, arg5);
  }

  public final void debug(Marker marker, String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6) {
    maybeLog(marker, DEBUG, format, arg1, arg2, arg3, arg4, arg5, arg6);
  }

  public final void debug(Marker marker, String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object... others) {
    maybeLog(marker, DEBUG, format, arg1, arg2, arg3, arg4, arg5, arg6, others);
  }

  // INFO

  public final boolean isInfoEnabled() {
    return isEnabled(null, INFO);
  }

  public final boolean isInfoEnabled(Marker marker) {
    return isEnabled(marker, INFO);
  }

  public final void info(String msg) {
    log(null, INFO, msg, null);
  }

  public final void info(String msg, Throwable t) {
    log(null, INFO, msg, t);
  }

  public final void info(String format, Object[] argArray) {
    maybeLog(null, INFO, format, argArray);
  }

  public final void info(String format, Object arg) {
    maybeLog(null, INFO, format, arg);
  }

  public final void info(String format, Object arg1, Object arg2) {
    maybeLog(null, INFO, format, arg1, arg2);
  }

  public final void info(String format, Object arg1, Object arg2, Object arg3) {
    maybeLog(null, INFO, format, arg1, arg2, arg3);
  }

  public final void info(String format, Object arg1, Object arg2, Object arg3, Object arg4) {
    maybeLog(null, INFO, format, arg1, arg2, arg3, arg4);
  }

  public final void info(String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5) {
    maybeLog(null, INFO, format, arg1, arg2, arg3, arg4, arg5);
  }

  public final void info(String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6) {
    maybeLog(null, INFO, format, arg1, arg2, arg3, arg4, arg5, arg6);
  }

  public final void info(String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object... others) {
    maybeLog(null, INFO, format, arg1, arg2, arg3, arg4, arg5, arg6, others);
  }

  public final void info(Marker marker, String msg) {
    log(marker, INFO, msg, null);
  }

  public final void info(Marker marker, String msg, Throwable t) {
    log(marker, INFO, msg, t);
  }

  public final void info(Marker marker, String format, Object[] argArray) {
    maybeLog(marker, INFO, format, argArray);
  }

  public final void info(Marker marker, String format, Object arg) {
    maybeLog(marker, INFO, format, arg);
  }

  public final void info(Marker marker, String format, Object arg1, Object arg2) {
    maybeLog(marker, INFO, format, arg1, arg2);
  }

  public final void info(Marker marker, String format, Object arg1, Object arg2, Object arg3) {
    maybeLog(marker, INFO, format, arg1, arg2, arg3);
  }

  public final void info(Marker marker, String format, Object arg1, Object arg2, Object arg3, Object arg4) {
    maybeLog(marker, INFO, format, arg1, arg2, arg3, arg4);
  }

  public final void info(Marker marker, String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5) {
    maybeLog(marker, INFO, format, arg1, arg2, arg3, arg4, arg5);
  }

  public final void info(Marker marker, String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6) {
    maybeLog(marker, INFO, format, arg1, arg2, arg3, arg4, arg5, arg6);
  }

  public final void info(Marker marker, String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object... others) {
    maybeLog(marker, INFO, format, arg1, arg2, arg3, arg4, arg5, arg6, others);
  }

  // WARN

  public final boolean isWarnEnabled() {
    return isEnabled(null, WARN);
  }

  public final boolean isWarnEnabled(Marker marker) {
    return isEnabled(marker, WARN);
  }

  public final void warn(String msg) {
    log(null, WARN, msg, null);
  }

  public final void warn(String msg, Throwable t) {
    log(null, WARN, msg, t);
  }

  public final void warn(String format, Object[] argArray) {
    maybeLog(null, WARN, format, argArray);
  }

  public final void warn(String format, Object arg) {
    maybeLog(null, WARN, format, arg);
  }

  public final void warn(String format, Object arg1, Object arg2) {
    maybeLog(null, WARN, format, arg1, arg2);
  }

  public final void warn(String format, Object arg1, Object arg2, Object arg3) {
    maybeLog(null, WARN, format, arg1, arg2, arg3);
  }

  public final void warn(String format, Object arg1, Object arg2, Object arg3, Object arg4) {
    maybeLog(null, WARN, format, arg1, arg2, arg3, arg4);
  }

  public final void warn(String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5) {
    maybeLog(null, WARN, format, arg1, arg2, arg3, arg4, arg5);
  }

  public final void warn(String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6) {
    maybeLog(null, WARN, format, arg1, arg2, arg3, arg4, arg5, arg6);
  }

  public final void warn(String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object... others) {
    maybeLog(null, WARN, format, arg1, arg2, arg3, arg4, arg5, arg6, others);
  }

  public final void warn(Marker marker, String msg) {
    log(marker, WARN, msg, null);
  }

  public final void warn(Marker marker, String msg, Throwable t) {
    log(marker, WARN, msg, t);
  }

  public final void warn(Marker marker, String format, Object[] argArray) {
    maybeLog(marker, WARN, format, argArray);
  }

  public final void warn(Marker marker, String format, Object arg) {
    maybeLog(marker, WARN, format, arg);
  }

  public final void warn(Marker marker, String format, Object arg1, Object arg2) {
    maybeLog(marker, WARN, format, arg1, arg2);
  }

  public final void warn(Marker marker, String format, Object arg1, Object arg2, Object arg3) {
    maybeLog(marker, WARN, format, arg1, arg2, arg3);
  }

  public final void warn(Marker marker, String format, Object arg1, Object arg2, Object arg3, Object arg4) {
    maybeLog(marker, WARN, format, arg1, arg2, arg3, arg4);
  }

  public final void warn(Marker marker, String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5) {
    maybeLog(marker, WARN, format, arg1, arg2, arg3, arg4, arg5);
  }

  public final void warn(Marker marker, String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6) {
    maybeLog(marker, WARN, format, arg1, arg2, arg3, arg4, arg5, arg6);
  }

  public final void warn(Marker marker, String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object... others) {
    maybeLog(marker, WARN, format, arg1, arg2, arg3, arg4, arg5, arg6, others);
  }

  // ERROR

  public final boolean isErrorEnabled() {
    return isEnabled(null, ERROR);
  }

  public final boolean isErrorEnabled(Marker marker) {
    return isEnabled(marker, ERROR);
  }

  public final void error(String msg) {
    log(null, ERROR, msg, null);
  }

  public final void error(String msg, Throwable t) {
    log(null, ERROR, msg, t);
  }

  public final void error(String format, Object[] argArray) {
    maybeLog(null, ERROR, format, argArray);
  }

  public final void error(String format, Object arg) {
    maybeLog(null, ERROR, format, arg);
  }

  public final void error(String format, Object arg1, Object arg2) {
    maybeLog(null, ERROR, format, arg1, arg2);
  }

  public final void error(String format, Object arg1, Object arg2, Object arg3) {
    maybeLog(null, ERROR, format, arg1, arg2, arg3);
  }

  public final void error(String format, Object arg1, Object arg2, Object arg3, Object arg4) {
    maybeLog(null, ERROR, format, arg1, arg2, arg3, arg4);
  }

  public final void error(String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5) {
    maybeLog(null, ERROR, format, arg1, arg2, arg3, arg4, arg5);
  }

  public final void error(String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6) {
    maybeLog(null, ERROR, format, arg1, arg2, arg3, arg4, arg5, arg6);
  }

  public final void error(String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object... others) {
    maybeLog(null, ERROR, format, arg1, arg2, arg3, arg4, arg5, arg6, others);
  }

  public final void error(Marker marker, String msg) {
    log(marker, ERROR, msg, null);
  }

  public final void error(Marker marker, String msg, Throwable t) {
    log(marker, ERROR, msg, t);
  }

  public final void error(Marker marker, String format, Object[] argArray) {
    maybeLog(marker, ERROR, format, argArray);
  }

  public final void error(Marker marker, String format, Object arg) {
    maybeLog(marker, ERROR, format, arg);
  }

  public final void error(Marker marker, String format, Object arg1, Object arg2) {
    maybeLog(marker, ERROR, format, arg1, arg2);
  }

  public final void error(Marker marker, String format, Object arg1, Object arg2, Object arg3) {
    maybeLog(marker, ERROR, format, arg1, arg2, arg3);
  }

  public final void error(Marker marker, String format, Object arg1, Object arg2, Object arg3, Object arg4) {
    maybeLog(marker, ERROR, format, arg1, arg2, arg3, arg4);
  }

  public final void error(Marker marker, String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5) {
    maybeLog(marker, ERROR, format, arg1, arg2, arg3, arg4, arg5);
  }

  public final void error(Marker marker, String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6) {
    maybeLog(marker, ERROR, format, arg1, arg2, arg3, arg4, arg5, arg6);
  }

  public final void error(Marker marker, String format, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object... others) {
    maybeLog(marker, ERROR, format, arg1, arg2, arg3, arg4, arg5, arg6, others);
  }

  // Private Methods

  private void maybeLog(Marker marker, Level level, String format, Object arg) {
    if (isEnabled(level)) {
      FormattingTuple ft = MessageFormatter.format(format, arg);
      log(marker, level, ft.getMessage(), ft.getThrowable());
    }
  }

  private void maybeLog(Marker marker, Level level, String format, Object arg1,
      Object arg2) {
    if (isEnabled(level)) {
      FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
      log(marker, level, ft.getMessage(), ft.getThrowable());
    }
  }

  private void maybeLog(Marker marker, Level level, String format, Object arg1,
      Object arg2, Object arg3) {
    if (isEnabled(level)) {
      FormattingTuple ft = MessageFormatter.arrayFormat(format, new Object[]{
          arg1, arg2, arg3});
      log(marker, level, ft.getMessage(), ft.getThrowable());
    }
  }

  private void maybeLog(Marker marker, Level level, String format, Object arg1,
      Object arg2, Object arg3, Object arg4) {
    if (isEnabled(level)) {
      FormattingTuple ft = MessageFormatter.arrayFormat(format, new Object[]{
          arg1, arg2, arg3, arg4});
      log(marker, level, ft.getMessage(), ft.getThrowable());
    }
  }

  private void maybeLog(Marker marker, Level level, String format, Object arg1,
      Object arg2, Object arg3, Object arg4, Object arg5) {
    if (isEnabled(level)) {
      FormattingTuple ft = MessageFormatter.arrayFormat(format, new Object[]{
          arg1, arg2, arg3, arg4, arg5});
      log(marker, level, ft.getMessage(), ft.getThrowable());
    }
  }

  private void maybeLog(Marker marker, Level level, String format, Object arg1,
      Object arg2, Object arg3, Object arg4, Object arg5, Object arg6) {
    if (isEnabled(level)) {
      FormattingTuple ft = MessageFormatter.arrayFormat(format, new Object[]{
          arg1, arg2, arg3, arg4, arg5, arg6});
      log(marker, level, ft.getMessage(), ft.getThrowable());
    }
  }

  private void maybeLog(Marker marker, Level level, String format, Object arg1,
      Object arg2, Object arg3, Object arg4, Object arg5, Object arg6,
      Object... others) {
    if (isEnabled(level)) {
      Object[] argArray = new Object[others.length + 6];
      argArray[0] = arg1;
      argArray[1] = arg2;
      argArray[2] = arg3;
      argArray[3] = arg4;
      argArray[4] = arg5;
      argArray[5] = arg6;
      System.arraycopy(others, 0, argArray, 6, others.length);
      FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
      log(marker, level, ft.getMessage(), ft.getThrowable());
    }
  }

  private void maybeLog(Marker marker, Level level, String format,
      Object[] argArray) {
    if (isEnabled(level)) {
      FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
      log(marker, level, ft.getMessage(), ft.getThrowable());
    }
  }

  private boolean isEnabled(Level level) {
    return isEnabled(null, level);
  }
}
