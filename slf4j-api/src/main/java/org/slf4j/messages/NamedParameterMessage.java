package org.slf4j.messages;

import java.util.Map;

public interface NamedParameterMessage extends Message {

  Map<String, Object> getNamedParameters();

}
