package org.slf4j.entries;

import java.util.Map;

public interface NamedParameterAwareEntry extends Entry {

  Map<String, Object> getNamedParameters();

}
