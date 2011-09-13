package org.slf4j.entries;

import org.slf4j.Marker;

public interface MarkerAwareEntry extends Entry {

  Marker getMarker();

}
