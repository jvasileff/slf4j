package org.slf4j.ext;

import org.slf4j.messages.ParameterizedMessage;

public class EventMessage implements ParameterizedMessage {

  private static final long serialVersionUID = -6745923052345545500L;

  private final EventData eventData;
  private transient String formattedMessage;

  public EventMessage(EventData eventData) {
    this.eventData = eventData;
  }

  public String getFormattedMessage() {
    if (formattedMessage == null) {
        formattedMessage = eventData.toXML();
    }
    return formattedMessage;
  }

  public Object[] getParameters() {
    return new Object[] {eventData};
  }
}
