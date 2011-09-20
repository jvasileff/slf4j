package org.slf4j;

  import java.io.Serializable;

import org.slf4j.helpers.FormattingTuple;

  public interface Formatter extends Serializable {

    FormattingTuple arrayFormat(String message, Object[] args);

}
