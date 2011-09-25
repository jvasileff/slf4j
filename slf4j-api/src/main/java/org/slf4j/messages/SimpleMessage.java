package org.slf4j.messages;

public class SimpleMessage implements ThrowableMessage, ParameterizedMessage {

  private static final long serialVersionUID = -8169425494726189881L;

  private boolean initialized = false;

  private final String message;
  private Object[] immutableArgs;

  private transient Object[] mutableArgs;
  private final transient Throwable throwable;

  public SimpleMessage(String message) {
    this(message, null, null);
  }

  public SimpleMessage(String message, Throwable throwable) {
    this(message, null, throwable);
  }

  public SimpleMessage(String message, Object[] params, Throwable throwable) {
    this.message = message;
    this.throwable = throwable;
    this.mutableArgs = params;
    initialize(); // this could be deferred with logging framework support
  }

  public String getFormattedMessage() {
    return message;
  }

  public Throwable getThrowable() {
    return throwable;
  }

  public Object[] getParameters() {
    initialize();
    return immutableArgs;
  }

  public void initialize() {
    if (initialized) {
      return;
    }

    immutableArgs = MessageUtils.newArrayOfImmutables(mutableArgs,  false);
    mutableArgs = null;
    initialized = true;
  }
}