package org.slf4j.cal10n;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.slf4j.messages.MessageUtils;
import org.slf4j.messages.ParameterizedMessage;
import org.slf4j.messages.ThrowableMessage;

import ch.qos.cal10n.IMessageConveyor;

public class LocLoggerMessage implements ParameterizedMessage, ThrowableMessage {

  private static final long serialVersionUID = -8655840927580085488L;

  /**
   * Every localized message logged by a LocLogger will bear this marker. It
   * allows marker-aware implementations to perform additional processing on
   * localized messages.
   */
  static Marker LOCALIZED = MarkerFactory.getMarker("LOCALIZED");

  private boolean initialized;

  private Object[] immutableArgs;
  private String formattedMessage = null;

  // save a few bytes
  private transient final Enum<?> key;

  // IMessageConveyor is not Serializable
  private transient IMessageConveyor imc;

  private transient Object[] mutableArgs;
  private transient Throwable throwable;

  public LocLoggerMessage(IMessageConveyor imc, Enum<?> key, Object[] args) {
    this.imc = imc;
    this.key = key;
    this.mutableArgs = args;
    initialize(); // this could be deferred with logging framework support
  }

  public Object[] getParameters() {
    initialize();
    return immutableArgs;
  }

  public String getFormattedMessage() {
    initialize();
    return formattedMessage;
  }

  public Throwable getThrowable() {
    initialize();
    return throwable;
  }

  private void initialize() {
    if (initialized) {
      return;
    }

    if (mutableArgs == null || mutableArgs.length == 0) {
      immutableArgs = null;
    } else {
      if (mutableArgs[mutableArgs.length-1] instanceof Throwable) {
          throwable = (Throwable) mutableArgs[mutableArgs.length-1];
      }
      immutableArgs = MessageUtils.newArrayOfImmutables(mutableArgs,
          throwable != null);
    }
    mutableArgs = null;

    // format now; imc is not Serializable
    formattedMessage = imc.getMessage(key, immutableArgs);
    imc = null;

    initialized = true;
  }
}
