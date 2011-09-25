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

package org.slf4j;

import org.slf4j.spi.LocationAwareLogger;

public enum Level {

  TRACE(LocationAwareLogger.TRACE_INT),
  DEBUG(LocationAwareLogger.DEBUG_INT),
  INFO(LocationAwareLogger.INFO_INT),
  WARN(LocationAwareLogger.WARN_INT),
  ERROR(LocationAwareLogger.ERROR_INT);

  private final int level;

  public int intValue() {
    return this.level;
  }

  private Level(int level) {
    this.level = level;
  }
  
  public static Level valueOfLevel(int level) {
    switch (level) {
      case LocationAwareLogger.TRACE_INT :
        return TRACE;
      case LocationAwareLogger.DEBUG_INT :
        return DEBUG;
      case LocationAwareLogger.INFO_INT :
        return INFO;
      case LocationAwareLogger.WARN_INT :
        return WARN;
      case LocationAwareLogger.ERROR_INT :
        return ERROR;
      default :
        throw new IllegalArgumentException("Level " + level
            + " is not recognized.");
    }
  }
}
