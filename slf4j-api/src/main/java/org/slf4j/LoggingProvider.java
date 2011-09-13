package org.slf4j;

import org.slf4j.entries.Entry;
import org.slf4j.helpers.Level;

public interface LoggingProvider {

  boolean isEnabledInternal(Marker marker, Level level);
  void logInternal(String fqcn, Entry entry);

}
