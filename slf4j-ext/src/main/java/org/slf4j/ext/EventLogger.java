package org.slf4j.ext;

import org.slf4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Simple Logger used to log events. All events are directed to a logger named "EventLogger"
 * with a level of INFO and with an Event marker.
 *
 * @author Ralph Goers
 */
public class EventLogger {

  private static final String FQCN = EventLogger.class.getName();

  static Marker EVENT_MARKER = MarkerFactory.getMarker("EVENT");

  private static Logger eventLogger = LoggerFactory.getLogger("EventLogger");

  /**
   * There can only be a single EventLogger.
   */
  private EventLogger() {
  }

  /**
   * Logs the event.
   *
   * @param data The EventData.
   */
  public static void logEvent(EventData data) {
    eventLogger.log(FQCN, EVENT_MARKER, Level.INFO, new EventMessage(data));
  }
}
