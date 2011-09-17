package org.slf4j.ext;

import org.slf4j.messages.ParameterizedMessage;

public class EventMessage implements ParameterizedMessage {

  private final EventData eventData;
  
  public EventMessage(EventData eventData) {
    this.eventData = eventData;
  }

  public String getFormattedMessage() {
    return eventData.toXML();
  }

  public Object[] getParameters() {
    return new Object[] {eventData};
  }
}
