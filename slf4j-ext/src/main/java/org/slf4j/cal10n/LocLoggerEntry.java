package org.slf4j.cal10n;

import org.slf4j.Level;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.slf4j.entries.Entry;
import org.slf4j.entries.MarkerAwareEntry;
import org.slf4j.entries.ParameterAwareEntry;

import ch.qos.cal10n.IMessageConveyor;

public class LocLoggerEntry implements Entry, ParameterAwareEntry,
    MarkerAwareEntry {

  /**
   * Every localized message logged by a LocLogger will bear this marker. It
   * allows marker-aware implementations to perform additional processing on
   * localized messages.
   */
  static Marker LOCALIZED = MarkerFactory.getMarker("LOCALIZED");
 
  private final Level level;
  private final IMessageConveyor imc;
  private final Enum<?> key;
  private final Object[] args;
  
  private boolean processed = false;
  private String message = null;

  public LocLoggerEntry(Level level, IMessageConveyor imc, Enum<?> key,
      Object[] args) {
    this.level = level;
    this.imc = imc;
    this.key = key;
    this.args = args;
  }

  public Object[] getParameters() {
    return args;
  }

  public Level getLevel() {
    return level;
  }

  public String getMessage() {
    process();
    return message;
  }

  public Marker getMarker() {
    return LOCALIZED;
  }

  private void process() {
    // TODO: not thread safe, should it be?
    if (! processed) {
      this.message = imc.getMessage(key, args);
    }
  }
}
