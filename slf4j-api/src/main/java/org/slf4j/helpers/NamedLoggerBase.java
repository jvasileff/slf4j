package org.slf4j.helpers;

import java.io.ObjectStreamException;
import java.io.Serializable;

import org.slf4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.messages.Message;

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

  public final void log(Marker marker, Level level, Message entry) {
    throw new UnsupportedOperationException(
        "This should be unreachable code; " +
        "a wrapper handles this method for legacy loggers.");
  }

  public final void log(String callerFQCN, Marker marker, Level level,
      Message entry) {
    throw new UnsupportedOperationException(
        "This should be unreachable code; " +
        "a wrapper handles this method for legacy loggers.");
  }
}
