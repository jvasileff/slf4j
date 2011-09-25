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

package org.slf4j.ext;

import org.slf4j.Level;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.AbstractLogger;
import org.slf4j.helpers.LegacyLoggerWrapper;
import org.slf4j.messages.Message;
import org.slf4j.spi.LocationAwareLogger;
import org.slf4j.spi.LoggerAdapter;

/**
 * A helper class wrapping an {@link org.slf4j.Logger} instance preserving
 * location information if the wrapped instance supports it.
 *
 * @author Ralph Goers
 * @author Ceki G&uuml;lc&uuml;
 * @author John Vasileff
 */
public class LoggerWrapper extends AbstractLogger {

  // FIXME: serialization/readResolve
  private static final long serialVersionUID = 8920070855870975205L;

  // To ensure consistency between two instances sharing the same name
  // (homonyms)
  // a LoggerWrapper should not contain any state beyond
  // the Logger instance it wraps.
  // fqcn depend on the caller, but its value would not be different
  // between successive invocations of a factory class

  @Deprecated // is direct access to this var necessary?
  protected final Logger logger;

  @Deprecated // we don't need this, keeping for compatibility with 1.6.x.
  String fqcn = LoggerWrapper.class.getName();

  // is this logger instance a LocationAwareLogger
  @Deprecated
  protected final boolean instanceofLAL;

  private final LoggerAdapter adapter;

  public LoggerWrapper(Logger logger) {
    this.logger = logger;

    // who knows how this class is used, logger may be slf4j 1.6.x.
    if (logger instanceof LoggerAdapter) {
      this.adapter = (LoggerAdapter) logger;
    } else {
      this.adapter = new LegacyLoggerWrapper(logger);
    }

    // for compatibility, we can't remove "protected ... instanceofLAL"
    if (logger instanceof LocationAwareLogger) {
      instanceofLAL = true;
    } else {
      instanceofLAL = false;
    }
  }

  @Deprecated // we don't need fqcn, keeping for compatibility with 1.6.x
  public LoggerWrapper(Logger logger, String fqcn) {
    this(logger);
    this.fqcn = fqcn;
  }

  public String getNameInternal() {
    return adapter.getNameInternal();
  }

  public boolean isEnabledInternal(Marker marker, Level level, Message message) {
    return adapter.isEnabledInternal(marker, level, message);
  }

  public void logInternal(String fqcn, Marker marker, Level level, Message entry) {
    adapter.logInternal(fqcn, marker, level, entry);
  }

  public Logger getWrappedLogger() {
    return logger;
  }
}
