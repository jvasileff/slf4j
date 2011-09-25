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
import org.slf4j.messages.Message;
import org.slf4j.messages.ParameterizedMessage;
import org.slf4j.messages.ThrowableMessage;
import org.slf4j.spi.LocationAwareLogger;

public class LegacyLoggerWrapper extends AbstractLogger {

  private Logger logger;
  private LocationAwareLogger lal = null;
  private boolean isLAL = false;
 
  public LegacyLoggerWrapper(Logger logger) {
    init(logger);
  }

  private void init(Logger logger) {
    this.logger = logger;
    if (logger instanceof LocationAwareLogger) {
      this.lal = (LocationAwareLogger) logger;
      this.isLAL = true;
    }
  }

  public String getNameInternal() {
    return logger.getName();
  }

  public boolean isEnabledInternal(Marker marker, Level level, Message message) {
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
        throw new IllegalStateException("Level " + level
            + " is not recognized.");
    }
  }

  public void logInternal(String fqcn, Marker marker, Level level, Message entry) {
    Throwable throwable = null;
    if (entry instanceof ThrowableMessage) {
      throwable = ((ThrowableMessage)entry).getThrowable();
    }
    if (isLAL) {
      Object[] params = null;
      if (entry instanceof ParameterizedMessage) {
        params = ((ParameterizedMessage)entry).getParameters();
      }
      lal.log(marker, fqcn, level.intValue(), entry.getFormattedMessage(),
          params, throwable);
    } else {
      switch(level) {
        case TRACE:
          logger.trace(marker, entry.getFormattedMessage(), throwable);
          break;
        case DEBUG:
          logger.debug(marker, entry.getFormattedMessage(), throwable);
          break;
        case INFO:
          logger.info(marker, entry.getFormattedMessage(), throwable);
          break;
        case WARN:
          logger.warn(marker, entry.getFormattedMessage(), throwable);
          break;
        case ERROR:
          logger.error(marker, entry.getFormattedMessage(), throwable);
          break;
        default:
          throw new IllegalStateException("Level " + level
              + " is not recognized.");
      }
    }
  }
}
