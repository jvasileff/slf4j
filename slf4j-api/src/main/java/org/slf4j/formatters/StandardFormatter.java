package org.slf4j.formatters;

import org.slf4j.Formatter;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

public final class StandardFormatter implements Formatter {

  private static final long serialVersionUID = 913366084435027526L;

  private static final StandardFormatter instance = new StandardFormatter();

  private StandardFormatter() {
    // singleton
  }

  public static StandardFormatter getInstance() {
    return instance;
  }

  public FormattingTuple arrayFormat(String message, Object[] args) {
    return MessageFormatter.arrayFormat(message, args);
  }

  /**
   * @return The singleton instance.
   */
  protected Object readResolve() {
          return getInstance();
  }

}
