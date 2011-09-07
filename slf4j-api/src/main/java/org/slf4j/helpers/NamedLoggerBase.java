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
}
