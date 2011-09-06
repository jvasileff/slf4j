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

/**
 * Serves as base class for named logger implementation. More significantly, this
 * class establishes deserialization behavior. See @see #readResolve. 
 * 
 * @author Ceki Gulcu
 * @since 1.5.3
 */
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

  private void log(Marker marker, Level level, String msg, Throwable t) {
    switch(level) {
      case TRACE :
        trace(marker, msg, t);
        break;
      case DEBUG :
        debug(marker, msg, t);
        break;
      case INFO :
        info(marker, msg, t);
        break;
      case WARN :
        warn(marker, msg, t);
        break;
      case ERROR :
        error(marker, msg, t);
        break;
      default:
        // will only happen if a new level is defined
        throw new IllegalStateException("Level " + level
            + " is not recognized.");
    }
  }
}
