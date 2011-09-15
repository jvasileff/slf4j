package org.slf4j.helpers;

import java.io.ObjectStreamException;
import java.io.Serializable;

import org.slf4j.LoggerFactory;
import org.slf4j.LoggingProvider;
import org.slf4j.formatters.StandardFormatter;

public abstract class AbstractLogger extends LoggerImpl implements
    Serializable, LoggingProvider {

  private static final long serialVersionUID = 3237785459601004498L;

  protected AbstractLogger() {
    super(StandardFormatter.getInstance());
    // would be better for the superclass to take the loggingProvider in the
    // constructor, but super(this) is not allowed.
    setLoggingProvider(this);
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