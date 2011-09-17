package org.slf4j.helpers;

import org.slf4j.Formatter;
import org.slf4j.Level;
import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.messages.FormattedMessage;
import org.slf4j.messages.MarkerMessage;
import org.slf4j.messages.Message;
import org.slf4j.messages.ParameterizedMessage;
import org.slf4j.messages.ThrowableMessage;

public class StandardMessage implements MarkerMessage,
    FormattedMessage, ParameterizedMessage, ThrowableMessage {

  private final Level level;
  private final Marker marker;
  private final String messagePattern;
  private final Object[] rawArgs;
  private final Formatter formatter;
  private FormattingTuple ft;

  public StandardMessage(
      Marker marker, Level level,
      String messagePattern, Object[] rawArgs,
      Formatter formatter) {

    this.marker = marker;
    this.level = level;
    this.messagePattern = messagePattern;
    this.rawArgs = rawArgs;
    this.formatter = formatter;
  }

  public String getFormattedMessage() {
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