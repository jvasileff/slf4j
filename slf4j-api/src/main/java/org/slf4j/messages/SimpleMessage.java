package org.slf4j.messages;


public class SimpleMessage implements ThrowableMessage, ParameterizedMessage {

  private static final long serialVersionUID = -8169425494726189881L;

  private final String message;
  private final transient Throwable throwable;
  private final transient Object[] parameters;

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