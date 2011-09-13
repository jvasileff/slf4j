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

import org.slf4j.Marker;
import org.slf4j.entries.Entry;
import org.slf4j.entries.ThrowableAwareEntry;
import org.slf4j.helpers.AbstractLogger;
import org.slf4j.helpers.Level;

/**
 * A simple (and direct) implementation that logs messages of level INFO or
 * higher on the console (<code>System.err<code>).
 * 
 * <p>The output includes the relative time in milliseconds, thread
 * name, the level, logger name, and the message followed by the line
 * separator for the host.  In log4j terms it amounts to the "%r [%t]
 * %level %logger - %m%n" pattern. </p>
 * 
 * <p>Sample output follows.</p>
<pre>
176 [main] INFO examples.Sort - Populating an array of 2 elements in reverse order.
225 [main] INFO examples.SortAlgo - Entered the sort method.
304 [main] INFO examples.SortAlgo - Dump of integer array:
317 [main] INFO examples.SortAlgo - Element [0] = 0
331 [main] INFO examples.SortAlgo - Element [1] = 1
343 [main] INFO examples.Sort - The next log statement should be an error message.
346 [main] ERROR examples.SortAlgo - Tried to dump an uninitialized array.
        at org.log4j.examples.SortAlgo.dump(SortAlgo.java:58)
        at org.log4j.examples.Sort.main(Sort.java:64)
467 [main] INFO  examples.Sort - Exiting main method.
</pre>
 * 
 * @author Ceki G&uuml;lc&uuml;
 */
public class SimpleLogger extends AbstractLogger {

  private static final long serialVersionUID = -3670490525882118863L;

  /**
   * Mark the time when this class gets loaded into memory.
   */
  private static long startTime = System.currentTimeMillis();
  public static final String LINE_SEPARATOR = System
      .getProperty("line.separator");
  private static String INFO_STR = "INFO";
  private static String WARN_STR = "WARN";
  private static String ERROR_STR = "ERROR";

  /**
   * Package access allows only {@link SimpleLoggerFactory} to instantiate
   * SimpleLogger instances.
   */
  SimpleLogger(String name) {
    super(name);
  }

  /**
   * This is our internal implementation for logging regular (non-parameterized)
   * log messages.
   * 
   * @param level
   * @param message
   * @param t
   */
  private void outputLog(String level, Entry entry) {
    Throwable throwable = null;
    if (entry instanceof ThrowableAwareEntry) {
      throwable = ((ThrowableAwareEntry)entry).getThrowable();
    }

    StringBuffer buf = new StringBuffer();

    long millis = System.currentTimeMillis();
    buf.append(millis - startTime);

    buf.append(" [");
    buf.append(Thread.currentThread().getName());
    buf.append("] ");

    buf.append(level);
    buf.append(" ");

    buf.append(getName());
    buf.append(" - ");

    buf.append(entry.getMessage());

    buf.append(LINE_SEPARATOR);

    System.err.print(buf.toString());
    if (throwable != null) {
      throwable.printStackTrace(System.err);
    }
    System.err.flush();
  }

  public boolean isEnabledInternal(Marker marker, Level level) {
    switch (level) {
      case TRACE :
      case DEBUG :
        return false;
      case INFO :
      case WARN :
      case ERROR :
        return true;
      default:
        // will only happen if a new level is defined
        throw new IllegalStateException("Level " + level
            + " is not recognized.");
    }
  }

  public void logInternal(String callerFqcn, Entry entry) {
    switch (entry.getLevel()) {
      case TRACE :
      case DEBUG :
        break;
      case INFO :
        outputLog(INFO_STR, entry);
        break;
      case WARN :
        outputLog(WARN_STR, entry);
        break;
      case ERROR :
        outputLog(ERROR_STR, entry);
        break;
      default:
        // will only happen if a new level is defined
        throw new IllegalStateException("Level " + entry.getLevel()
            + " is not recognized.");
    }
  }
}
