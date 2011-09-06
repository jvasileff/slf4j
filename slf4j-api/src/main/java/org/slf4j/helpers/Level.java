package org.slf4j.helpers;

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

}
