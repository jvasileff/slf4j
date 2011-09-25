/* 
 * Copyright (c) 2004-2011 QOS.ch
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

package org.slf4j.helpers;

import org.slf4j.Level;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.messages.DefaultMessage;
import org.slf4j.messages.SimpleMessage;

public class LoggerUtils {

  private LoggerUtils() {
    // no instances;
  }

  public static void logObject(String fqcn, Logger logger, Marker marker,
      Level level, Object message) {
    logObject(fqcn, logger, marker, level, message, (Throwable) null);
  }

  public static void logObject(String fqcn, Logger logger, Marker marker,
      Level level, Object message, Throwable t) {
    if (isEnabled(logger, marker, level)) {
      String msg = String.valueOf(message);
      logger.log(fqcn, marker, level, new SimpleMessage(msg, t));
    }
  }

  public static void log(String fqcn, Logger logger, Marker marker,
      Level level, String message) {
    logObject(fqcn, logger, marker, level, message, (Throwable) null);
  }

  public static void log(String fqcn, Logger logger, Marker marker,
      Level level, String message, Throwable t) {
    if (isEnabled(logger, marker, level)) {
      logger.log(fqcn, marker, level, new SimpleMessage(message, t));
    }
  }

  public static void log(String fqcn, Logger logger, Marker marker,
      Level level, String format, Object... args) {
    if (isEnabled(logger, marker, level)) {
      logger.log(fqcn, marker, level, new DefaultMessage(format, args));
    }
  }

  public static boolean isEnabled(Logger logger, Marker marker, Level level) {
    switch(level) {
      case TRACE:
        return logger.isTraceEnabled(marker);
      case DEBUG:
        return logger.isDebugEnabled(marker);
      case INFO:
        return logger.isInfoEnabled(marker);
      case WARN:
        return logger.isWarnEnabled(marker);
      case ERROR:
        return logger.isErrorEnabled(marker);
      default:
        // will only happen if a new level is defined
        throw new IllegalArgumentException("Level " + level
            + " is not recognized.");
    }
  }
}
