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
