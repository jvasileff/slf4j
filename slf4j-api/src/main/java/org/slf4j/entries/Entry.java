package org.slf4j.entries;

import org.slf4j.helpers.Level;

public interface Entry {

  Level getLevel();
  String getMessage();

}
