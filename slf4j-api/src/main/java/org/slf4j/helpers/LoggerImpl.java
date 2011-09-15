package org.slf4j.helpers;

import static org.slf4j.Level.DEBUG;
import static org.slf4j.Level.ERROR;
import static org.slf4j.Level.INFO;
import static org.slf4j.Level.TRACE;
import static org.slf4j.Level.WARN;

import java.io.Serializable;

import org.slf4j.Formatter;
import org.slf4j.Level;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.entries.Entry;
import org.slf4j.spi.LocationAwareLogger;
import org.slf4j.spi.LoggerAdapter;

public class LoggerImpl implements LocationAwareLogger, Serializable {

  private static final long serialVersionUID = 3237785459601004498L;

  private static final String FQCN = LoggerImpl.class.getName();

  private final Formatter formatter;
  private LoggerAdapter provider;

  public LoggerImpl(LoggerAdapter provider, Formatter formatter) {
    this.provider = provider;
    this.formatter = formatter;
  }

  public LoggerImpl(Formatter formatter) {
    this.formatter = formatter;
  }

  protected void setLoggingProvider(LoggerAdapter provider) {
    this.provider = provider;
  }

  protected LoggerAdapter getLoggingProvider() {
    return provider;
  }

  public final String getName() {
    return provider.getNameInternal();
  }

  public final void log(Entry entry) {
    provider.logInternal(FQCN, entry);
  }

  public final void log(String callerFQCN, Entry entry) {
    provider.logInternal(callerFQCN, entry);
  }

  // TRACE

  public final boolean isTraceEnabled() {
    return provider.isEnabledInternal(null, TRACE);
  }

  public final boolean isTraceEnabled(Marker marker) {
    return provider.isEnabledInternal(marker, TRACE);
  }

  public final void trace(Entry entry) {
    provider.logInternal(FQCN, entry);
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
    return provider.isEnabledInternal(null, DEBUG);
  }

  public final boolean isDebugEnabled(Marker marker) {
    return provider.isEnabledInternal(marker, DEBUG);
  }

  public final void debug(Entry entry) {
    provider.logInternal(FQCN, entry);
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
    return provider.isEnabledInternal(null, INFO);
  }

  public final boolean isInfoEnabled(Marker marker) {
    return provider.isEnabledInternal(marker, INFO);
  }

  public final void info(Entry entry) {
    provider.logInternal(FQCN, entry);
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
    return provider.isEnabledInternal(null, WARN);
  }

  public final boolean isWarnEnabled(Marker marker) {
    return provider.isEnabledInternal(marker, WARN);
  }

  public final void warn(Entry entry) {
    provider.logInternal(FQCN, entry);
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
    return provider.isEnabledInternal(null, ERROR);
  }

  public final boolean isErrorEnabled(Marker marker) {
    return provider.isEnabledInternal(marker, ERROR);
  }

  public final void error(Entry entry) {
    provider.logInternal(FQCN, entry);
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
    if (provider.isEnabledInternal(marker, level)) {
      provider.logInternal(FQCN, new SimpleEntry(marker, level, message, t));
    }
  }

  private void maybeLog(Marker marker, Level level,
      String format, Object arg) {
    if (provider.isEnabledInternal(marker, level)) {
      provider.logInternal(FQCN, new StandardEntry(marker, level, format,
          new Object[] {arg}, formatter));
    }
  }

  private void maybeLog(Marker marker, Level level,
      String format, Object arg1, Object arg2) {
    if (provider.isEnabledInternal(marker, level)) {
      provider.logInternal(FQCN, new StandardEntry(marker, level, format,
          new Object[] {arg1, arg2}, formatter));
    }
  }

  private void maybeLog(Marker marker, Level level,
      String format, Object[] args) {
    if (provider.isEnabledInternal(marker, level)) {
      provider.logInternal(FQCN, new StandardEntry(marker, level, format,
          args, formatter));
    }
  }

  public Logger withFormatter(Formatter formatter) {
    return new LoggerImpl(this.provider, formatter);
  }

  public Formatter getFormatter() {
    return formatter;
  }

  public void log(Marker marker, String fqcn, int level, String message,
      Object[] argArray, Throwable t) {
    log(fqcn, new SimpleEntry(
        marker, Level.valueOfLevel(level), message, argArray, t));
  }
}