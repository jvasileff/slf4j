package org.slf4j.helpers;

import org.slf4j.Formatter;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.messages.FormattedMessage;
import org.slf4j.messages.ParameterizedMessage;
import org.slf4j.messages.ThrowableMessage;

public class StandardMessage implements FormattedMessage, ParameterizedMessage,
    ThrowableMessage {

  private final String messagePattern;
  private final Object[] rawArgs;
  private final Formatter formatter;
  private FormattingTuple ft;

  public StandardMessage(String messagePattern, Object[] rawArgs, Formatter formatter) {
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

  private FormattingTuple process() {
    // TODO: not thread safe, do we care?
    if (ft == null) {
      ft = formatter.arrayFormat(messagePattern, rawArgs);
    }
    return ft;
  }
}