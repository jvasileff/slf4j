package org.slf4j.ext;

import org.slf4j.Level;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.slf4j.messages.MarkerMessage;
import org.slf4j.messages.Message;
import org.slf4j.messages.ParameterizedMessage;

public class EventMessage implements MarkerMessage, ParameterizedMessage {

  private static final Marker EVENT_MARKER = MarkerFactory.getMarker("EVENT");
  
  private final EventData eventData;
  
  public EventMessage(EventData eventData) {
    this.eventData = eventData;
  }

  public Marker getMarker() {
    return EVENT_MARKER;
  }

  public Level getLevel() {
    return Level.INFO;
  }

  public String getFormattedMessage() {
    return eventData.toXML();
  }

  public Object[] getParameters() {
    return new Object[] {eventData};
  }

}
