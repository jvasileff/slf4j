package org.slf4j.spi;

import org.slf4j.Level;
import org.slf4j.Marker;
import org.slf4j.entries.Entry;

public interface LoggerAdapter {

  String getNameInternal();
  boolean isEnabledInternal(Marker marker, Level level);
  void logInternal(String fqcn, Entry entry);

}
