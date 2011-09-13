package org.slf4j;

  import org.slf4j.helpers.FormattingTuple;

  public interface Formatter {

    FormattingTuple arrayFormat(String message, Object[] args);

}
