package org.slf4j.spi;

import org.slf4j.Level;
import org.slf4j.Marker;
import org.slf4j.messages.Message;

public interface LoggerAdapter {

  String getNameInternal();
  boolean isEnabledInternal(Marker marker, Level level, Message entry);
  void logInternal(String fqcn, Marker marker, Level level, Message entry);

}
