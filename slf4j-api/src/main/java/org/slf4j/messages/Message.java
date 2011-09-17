package org.slf4j.messages;

import org.slf4j.Level;

public interface Message {

  Level getLevel();
  String getFormattedMessage();

}
