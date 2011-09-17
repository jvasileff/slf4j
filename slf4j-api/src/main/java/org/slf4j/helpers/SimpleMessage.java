package org.slf4j.helpers;

import org.slf4j.messages.ParameterizedMessage;
import org.slf4j.messages.ThrowableMessage;

public class SimpleMessage implements ThrowableMessage, ParameterizedMessage {

  private final String message;
  private final Throwable throwable;
  private final Object[] parameters;

  public SimpleMessage(String message) {
    this(message, null, null);
  }

  public SimpleMessage(String message, Throwable throwable) {
    this(message, null, throwable);
  }

  public SimpleMessage(String message, Object[] params, Throwable throwable) {
    this.message = message;
    this.throwable = throwable;
    this.parameters = params;
  }

  public String getFormattedMessage() {
    return message;
  }

  public Throwable getThrowable() {
    return throwable;
  }

  public Object[] getParameters() {
    return parameters;
  }
}