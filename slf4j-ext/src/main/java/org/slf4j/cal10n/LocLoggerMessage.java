package org.slf4j.cal10n;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.slf4j.messages.ParameterizedMessage;

import ch.qos.cal10n.IMessageConveyor;

public class LocLoggerMessage implements ParameterizedMessage {

  /**
   * Every localized message logged by a LocLogger will bear this marker. It
   * allows marker-aware implementations to perform additional processing on
   * localized messages.
   */
  static Marker LOCALIZED = MarkerFactory.getMarker("LOCALIZED");
 
  private final IMessageConveyor imc;
  private final Enum<?> key;
  private final Object[] args;
  
  private boolean processed = false;
  private String message = null;

  public LocLoggerMessage(IMessageConveyor imc, Enum<?> key,
      Object[] args) {
    this.imc = imc;
    this.key = key;
    this.args = args;
  }

  public Object[] getParameters() {
    return args;
  }

  public String getFormattedMessage() {
    process();
    return message;
  }

  private void process() {
    // TODO: not thread safe, should it be?
    if (! processed) {
      this.message = imc.getMessage(key, args);
    }
  }
}
