package org.slf4j.helpers;

import org.slf4j.Formatter;
import org.slf4j.Marker;
import org.slf4j.entries.Entry;
import org.slf4j.entries.FormattedMessageEntry;
import org.slf4j.entries.MarkerAwareEntry;
import org.slf4j.entries.ParameterAwareEntry;
import org.slf4j.entries.ThrowableAwareEntry;
import org.slf4j.formatters.StandardFormatter;
import org.slf4j.helpers.FormattingTuple;

public class StandardEntry implements Entry, MarkerAwareEntry,
    FormattedMessageEntry, ParameterAwareEntry, ThrowableAwareEntry {

  private final Level level;
  private final Marker marker;
  private final String messagePattern;
  private final Object[] rawArgs;
  private final Formatter formatter;
  private FormattingTuple ft;

  public StandardEntry(
      Marker marker, Level level,
      String messagePattern, Object[] rawArgs) {

    this(marker, level, messagePattern, rawArgs,
        StandardFormatter.getInstance());
  }

  private StandardEntry(
      Marker marker, Level level,
      String messagePattern, Object[] rawArgs,
      Formatter formatter) {

    this.marker = marker;
    this.level = level;
    this.messagePattern = messagePattern;
    this.rawArgs = rawArgs;
    this.formatter = formatter;
  }

  public String getMessage() {
    return process().getMessage();
  }

  public String getMessagePattern() {
    return messagePattern;
  }

  public Object[] getParameters() {
    return process().getArgArray();
  }

  public Throwable getThrowable() {
    return process().getThrowable();
  }

  public Level getLevel() {
    return level;
  }

  public Marker getMarker() {
    return marker;
  }

  private FormattingTuple process() {
    // TODO: not thread safe, do we care?
    if (ft == null) {
      ft = formatter.arrayFormat(messagePattern, rawArgs);
    }
    return ft;
  }
}