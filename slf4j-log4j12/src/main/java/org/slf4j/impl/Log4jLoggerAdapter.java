/*
 * Copyright (c) 2004-2008 QOS.ch
 * All rights reserved.
 * 
 * Permission is hereby granted, free  of charge, to any person obtaining
 * a  copy  of this  software  and  associated  documentation files  (the
 * "Software"), to  deal in  the Software without  restriction, including
 * without limitation  the rights to  use, copy, modify,  merge, publish,
 * distribute,  sublicense, and/or sell  copies of  the Software,  and to
 * permit persons to whom the Software  is furnished to do so, subject to
 * the following conditions:
 * 
 * The  above  copyright  notice  and  this permission  notice  shall  be
 * included in all copies or substantial portions of the Software.
 * 
 * THE  SOFTWARE IS  PROVIDED  "AS  IS", WITHOUT  WARRANTY  OF ANY  KIND,
 * EXPRESS OR  IMPLIED, INCLUDING  BUT NOT LIMITED  TO THE  WARRANTIES OF
 * MERCHANTABILITY,    FITNESS    FOR    A   PARTICULAR    PURPOSE    AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE,  ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.slf4j.impl;

import org.apache.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.AbstractLogger;
import org.slf4j.messages.Message;
import org.slf4j.messages.ThrowableMessage;

/**
 * A wrapper over {@link org.apache.log4j.Logger org.apache.log4j.Logger} in
 * conforming to the {@link Logger} interface.
 * 
 * <p>
 * Note that the logging levels mentioned in this class refer to those defined
 * in the <a
 * href="http://logging.apache.org/log4j/docs/api/org/apache/log4j/Level.html">
 * <code>org.apache.log4j.Level</code></a> class.
 * 
 * <p>
 * The TRACE level was introduced in log4j version 1.2.12. In order to avoid
 * crashing the host application, in the case the log4j version in use predates
 * 1.2.12, the TRACE level will be mapped as DEBUG. See also <a
 * href="http://bugzilla.slf4j.org/show_bug.cgi?id=68">bug 68</a>.
 * 
 * @author Ceki G&uuml;lc&uuml;
 */
public final class Log4jLoggerAdapter extends AbstractLogger {

  private static final long serialVersionUID = 6182834493563598289L;

  final transient org.apache.log4j.Logger logger;

  /**
   * Following the pattern discussed in pages 162 through 168 of "The complete
   * log4j manual".
   */
  final static String FQCN = Log4jLoggerAdapter.class.getName();

  // Does the log4j version in use recognize the TRACE level?
  // The trace level was introduced in log4j 1.2.12.
  final boolean traceCapable;
  private final String name;

  // WARN: Log4jLoggerAdapter constructor should have only package access so
  // that
  // only Log4jLoggerFactory be able to create one.
  Log4jLoggerAdapter(org.apache.log4j.Logger logger) {
    this.name = logger.getName();
    this.logger = logger;
    traceCapable = isTraceCapable();
  }
  
  public String getNameInternal() {
    return name;
  }

  private boolean isTraceCapable() {
    try {
      logger.isTraceEnabled();
      return true;
    } catch (NoSuchMethodError e) {
      return false;
    }
  }

  public boolean isEnabledInternal(Marker marker,
      org.slf4j.Level level) {
    switch (level) {
      case TRACE :
        return traceCapable ? logger.isTraceEnabled() : logger.isDebugEnabled();
      case DEBUG :
        return logger.isDebugEnabled();
      case INFO :
        return logger.isInfoEnabled();
      case WARN :
        return logger.isEnabledFor(Level.WARN);
      case ERROR :
        return logger.isEnabledFor(Level.ERROR);
      default:
        // will only happen if a new level is defined
        throw new IllegalStateException("Level " + level
            + " is not recognized.");
    }
  }

  public void logInternal(String callerFqcn, Message entry) {
    Throwable throwable = null;
    if (entry instanceof ThrowableMessage) {
      throwable = ((ThrowableMessage)entry).getThrowable();
    }
    Level log4jLevel;
    switch (entry.getLevel()) {
    case TRACE:
      log4jLevel = traceCapable ? Level.TRACE : Level.DEBUG;
      break;
    case DEBUG:
      log4jLevel = Level.DEBUG;
      break;
    case INFO:
      log4jLevel = Level.INFO;
      break;
    case WARN:
      log4jLevel = Level.WARN;
      break;
    case ERROR:
      log4jLevel = Level.ERROR;
      break;
    default:
      throw new IllegalStateException("Level " + entry.getLevel()
          + " is not recognized.");
    }
    logger.log(callerFqcn, log4jLevel, entry.getFormattedMessage(), throwable);
  }
}
