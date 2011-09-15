package org.slf4j.helpers;

import org.slf4j.Formatter;
import org.slf4j.Level;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.entries.Entry;
import org.slf4j.spi.LoggerAdapter;

public class LoggerImpl extends AbstractLogger {

  private static final long serialVersionUID = -8316269301520050575L;

  private final LoggerAdapter adapter;

  public LoggerImpl(LoggerAdapter adapter) {
    this.adapter = adapter;
  }

  public LoggerImpl(LoggerAdapter adapter, Formatter formatter) {
    super(formatter);
    this.adapter = adapter;
  }

  public String getNameInternal() {
    return adapter.getNameInternal();
  }

  public boolean isEnabledInternal(Marker marker, Level level) {
    return adapter.isEnabledInternal(marker, level);
  }

  public void logInternal(String fqcn, Entry entry) {
    adapter.logInternal(fqcn, entry);
  }

  public Logger withFormatter(Formatter formatter) {
    return new LoggerImpl(adapter, formatter);
  }
}
