package org.slf4j.ext;

import org.slf4j.Level;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.slf4j.entries.Entry;
import org.slf4j.entries.MarkerAwareEntry;
import org.slf4j.entries.ParameterAwareEntry;

public class EventEntry implements Entry, MarkerAwareEntry, ParameterAwareEntry {

  private static final Marker EVENT_MARKER = MarkerFactory.getMarker("EVENT");
  
  private final EventData eventData;
  
  public EventEntry(EventData eventData) {
    this.eventData = eventData;
  }

  public Marker getMarker() {
    return EVENT_MARKER;
  }

  public Level getLevel() {
    return Level.INFO;
  }

  public String getMessage() {
    return eventData.toXML();
  }

  public Object[] getParameters() {
    return new Object[] {eventData};
  }

}
