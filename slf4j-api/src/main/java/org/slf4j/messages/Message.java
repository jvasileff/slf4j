package org.slf4j.messages;

import java.io.Serializable;

public interface Message extends Serializable {

  String getFormattedMessage();

}
