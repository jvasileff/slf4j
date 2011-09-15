package org.slf4j.entries;

import org.slf4j.Level;

public interface Entry {

  Level getLevel();
  String getMessage();

}
