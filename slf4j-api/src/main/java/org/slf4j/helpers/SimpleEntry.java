package org.slf4j.helpers;

import org.slf4j.Level;
import org.slf4j.Marker;
import org.slf4j.entries.Entry;
import org.slf4j.entries.MarkerAwareEntry;
import org.slf4j.entries.ParameterAwareEntry;
import org.slf4j.entries.ThrowableAwareEntry;

public class SimpleEntry implements Entry, MarkerAwareEntry,
    ThrowableAwareEntry, ParameterAwareEntry {

  private final Level level;
  private final Marker marker;
  private final String message;
  private final Throwable throwable;
  private final Object[] parameters;

  public SimpleEntry(
      Marker marker, Level level, String message, Throwable throwable) {
    this(marker, level, message, null, throwable);
  }

  public SimpleEntry(
      Marker marker, Level level, String message, Object[] params,
      Throwable throwable) {
    this.marker = marker;
    this.level = level;
    this.message = message;
    this.throwable = throwable;
    this.parameters = params;
  }

  public String getMessage() {
    return message;
  }

  public Throwable getThrowable() {
    return throwable;
  }

  public Level getLevel() {
    return level;
  }

  public Marker getMarker() {
    return marker;
  }
  
  public Object[] getParameters() {
    return parameters;
  }
}