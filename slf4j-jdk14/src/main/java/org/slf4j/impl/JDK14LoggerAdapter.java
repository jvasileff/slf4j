/*
 * Copyright (c) 2004-2005 SLF4J.ORG
 * Copyright (c) 2004-2005 QOS.ch
 *
 * All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to  deal in  the Software without  restriction, including
 * without limitation  the rights to  use, copy, modify,  merge, publish,
 * distribute, and/or sell copies of  the Software, and to permit persons
 * to whom  the Software is furnished  to do so, provided  that the above
 * copyright notice(s) and this permission notice appear in all copies of
 * the  Software and  that both  the above  copyright notice(s)  and this
 * permission notice appear in supporting documentation.
 *
 * THE  SOFTWARE IS  PROVIDED  "AS  IS", WITHOUT  WARRANTY  OF ANY  KIND,
 * EXPRESS OR  IMPLIED, INCLUDING  BUT NOT LIMITED  TO THE  WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR  A PARTICULAR PURPOSE AND NONINFRINGEMENT
 * OF  THIRD PARTY  RIGHTS. IN  NO EVENT  SHALL THE  COPYRIGHT  HOLDER OR
 * HOLDERS  INCLUDED IN  THIS  NOTICE BE  LIABLE  FOR ANY  CLAIM, OR  ANY
 * SPECIAL INDIRECT  OR CONSEQUENTIAL DAMAGES, OR  ANY DAMAGES WHATSOEVER
 * RESULTING FROM LOSS  OF USE, DATA OR PROFITS, WHETHER  IN AN ACTION OF
 * CONTRACT, NEGLIGENCE  OR OTHER TORTIOUS  ACTION, ARISING OUT OF  OR IN
 * CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 * Except as  contained in  this notice, the  name of a  copyright holder
 * shall not be used in advertising or otherwise to promote the sale, use
 * or other dealings in this Software without prior written authorization
 * of the copyright holder.
 *
 */

package org.slf4j.impl;

import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.AbstractLogger;
import org.slf4j.messages.Message;
import org.slf4j.messages.ThrowableMessage;

/**
 * A wrapper over {@link java.util.logging.Logger java.util.logging.Logger} in
 * conformity with the {@link Logger} interface. Note that the logging levels
 * mentioned in this class refer to those defined in the java.util.logging
 * package.
 * 
 * @author Ceki G&uuml;lc&uuml;
 * @author Peter Royal
 */
public final class JDK14LoggerAdapter extends AbstractLogger {

  private static final long serialVersionUID = -8053026990503422791L;

  private final String name;
  final java.util.logging.Logger logger;

  // WARN: JDK14LoggerAdapter constructor should have only package access so
  // that only JDK14LoggerFactory be able to create one.
  JDK14LoggerAdapter(java.util.logging.Logger logger) {
    this.name = logger.getName();
    this.logger = logger;
  }

  public String getNameInternal() {
    return name;
  }

  /**
   * Log the message at the specified level with the specified throwable if any.
   * This method creates a LogRecord and fills in caller date before calling
   * this instance's JDK14 logger.
   * 
   * See bug report #13 for more details.
   * 
   * @param level
   * @param msg
   * @param t
   */
  private void log(String callerFQCN, Level level, String msg, Throwable t) {
    // millis and thread are filled by the constructor
    LogRecord record = new LogRecord(level, msg);
    record.setLoggerName(getName());
    record.setThrown(t);
    fillCallerData(callerFQCN, record);
    logger.log(record);

  }

  static String SUPER = AbstractLogger.class.getName();

  /**
   * Fill in caller data if possible.
   * 
   * @param record
   *          The record to update
   */
  final private void fillCallerData(String callerFQCN, LogRecord record) {
    StackTraceElement[] steArray = new Throwable().getStackTrace();

    int selfIndex = -1;
    for (int i = 0; i < steArray.length; i++) {
      final String className = steArray[i].getClassName();
      if (className.equals(callerFQCN) || className.equals(SUPER)) {
        selfIndex = i;
        break;
      }
    }

    int found = -1;
    for (int i = selfIndex + 1; i < steArray.length; i++) {
      final String className = steArray[i].getClassName();
      if (!(className.equals(callerFQCN) || className.equals(SUPER))) {
        found = i;
        break;
      }
    }

    if (found != -1) {
      StackTraceElement ste = steArray[found];
      // setting the class name has the side effect of setting
      // the needToInferCaller variable to false.
      record.setSourceClassName(ste.getClassName());
      record.setSourceMethodName(ste.getMethodName());
    }
  }

  public void logInternal(String callerFQCN, Marker marker,
      org.slf4j.Level level, Message entry) {

    Level julLevel = toJulLevel(level);

    // the logger.isLoggable check avoids the unconditional
    // construction of location data for disabled log
    // statements. As of 2008-07-31, callers of this method
    // do not perform this check. See also
    // http://bugzilla.slf4j.org/show_bug.cgi?id=90
    if (logger.isLoggable(julLevel)) {
      Throwable throwable = null;
      if (entry instanceof ThrowableMessage) {
        throwable = ((ThrowableMessage)entry).getThrowable();
      }
      log(callerFQCN, julLevel, entry.getFormattedMessage(), throwable);
    }
  }

  public boolean isEnabledInternal(Marker marker, org.slf4j.Level level,
      Message message) {

    return logger.isLoggable(toJulLevel(level));
  }

  private static Level toJulLevel(org.slf4j.Level level) {
    switch (level) {
      case TRACE :
        return Level.FINEST;
      case DEBUG :
        return Level.FINE;
      case INFO :
        return Level.INFO;
      case WARN :
        return Level.WARNING;
      case ERROR :
        return Level.SEVERE;
      default:
        // will only happen if a new level is defined
        throw new IllegalArgumentException("Level " + level
            + " is not recognized.");
    }
  }
}
