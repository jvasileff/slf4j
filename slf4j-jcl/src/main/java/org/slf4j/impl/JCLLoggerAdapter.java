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

import org.apache.commons.logging.Log;
import org.slf4j.Level;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.entries.Entry;
import org.slf4j.entries.ThrowableAwareEntry;
import org.slf4j.helpers.AbstractLogger;

/**
 * A wrapper over {@link org.apache.commons.logging.Log
 * org.apache.commons.logging.Log} in conformance with the {@link Logger}
 * interface.
 * 
 * @author Ceki G&uuml;lc&uuml;
 */
public final class JCLLoggerAdapter extends AbstractLogger {

  private static final long serialVersionUID = 4646510625595243323L;
  private final String name;
  final Log log;

  // WARN: JCLLoggerAdapter constructor should have only package access so
  // that only JCLLoggerFactory be able to create one.
  JCLLoggerAdapter(Log log, String name) {
    this.log = log;
    this.name = name;
  }

  public String getNameInternal() {
    return name;
  }

  public boolean isEnabledInternal(Marker marker, Level level) {
    switch (level) {
      case TRACE :
        return log.isTraceEnabled();
      case DEBUG :
        return log.isDebugEnabled();
      case INFO :
        return log.isInfoEnabled();
      case WARN :
        return log.isWarnEnabled();
      case ERROR :
        return log.isErrorEnabled();
      default:
        // will only happen if a new level is defined
        throw new IllegalStateException("Level " + level
            + " is not recognized.");
    }
  }

  public void logInternal(String callerFqcn, Entry entry) {
    String msg = entry.getMessage();
    Throwable t = null;
    if (entry instanceof ThrowableAwareEntry) {
      t = ((ThrowableAwareEntry)entry).getThrowable();
    }
    switch (entry.getLevel()) {
      case TRACE :
        if (t != null) {
          log.trace(msg, t);
        } else {
          log.trace(msg);
        }
        break;
      case DEBUG :
        if (t != null) {
          log.debug(msg, t);
        } else {
          log.debug(msg);
        }
        break;
      case INFO :
        if (t != null) {
          log.info(msg, t);
        } else {
          log.info(msg);
        }
        break;
      case WARN :
        if (t != null) {
          log.warn(msg, t);
        } else {
          log.warn(msg);
        }
        break;
      case ERROR :
        if (t != null) {
          log.error(msg, t);
        } else {
          log.error(msg);
        }
        break;
      default:
        // will only happen if a new level is defined
        throw new IllegalStateException("Level " + entry.getLevel()
            + " is not recognized.");
    }
  }
}
