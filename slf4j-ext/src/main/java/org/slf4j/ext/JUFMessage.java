package org.slf4j.ext;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Formatter;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.messages.FormattedMessage;
import org.slf4j.messages.ParameterizedMessage;
import org.slf4j.messages.ThrowableMessage;

/**
 * Message implementation that's using java.util.Formatter to format the message.
 * This essentially provides everything that http://bugzilla.slf4j.org/show_bug.cgi?id=116
 * requested.
 * Please be aware that this Message is about 8 times slower than ParameterizedMessage!
 * ( http://bugzilla.slf4j.org/show_bug.cgi?id=116#c5 )
 * Also, this class is not Serializable since args are not guaranteed to be Serializable and there
 * is no conversion that can performed that would make the arguments Serializable an would not break
 * formatting, for example in case of java.util.Date.
 *
 * @author J&ouml;rn Huxhorn
 * @author John Vasileff
 */
public class JUFMessage implements FormattedMessage, ParameterizedMessage,ThrowableMessage {

  private static final long serialVersionUID = -5744822917248225652L;

  private boolean initialized = false;

  private final Locale locale;
  private final String format;

  private Object[] immutableArgs;
  private String formattedMessage;

  private transient Object[] mutableArgs;
  private transient Throwable throwable;

  public JUFMessage(String format, Object... args) {
    this(null, format, args);
  }

  public JUFMessage(Locale locale, String format, Object... args) {
    this.locale = locale;
    this.format = format;
    this.mutableArgs = args;
    initialize(); // this could be deferred with logging framework support
  }

  public String getMessagePattern() {
    return format;
  }

  public Object[] getParameters() {
    initialize();
    return immutableArgs;
  }

  public Throwable getThrowable() {
    initialize();
    return throwable;
  }

  public String getFormattedMessage() {
    initialize();
    if (formattedMessage == null) {
      try {
        Formatter formatter = new Formatter();
        formattedMessage = formatter.format(locale, format, immutableArgs)
            .out().toString();
      } catch (Exception t) {
        // we must not throw an exception here!
        formattedMessage = t.toString();
      }
    }
    return formattedMessage;
  }

  /**
   * Initialize this.immutableArgs and this.throwable.
   */
  public void initialize() {
    if (initialized) {
      return;
    }

    if(mutableArgs == null || mutableArgs.length == 0) {
      immutableArgs = null;
      initialized = true;
      return;
    }

    if(mutableArgs[mutableArgs.length-1] instanceof Throwable) {
      throwable = (Throwable) mutableArgs[mutableArgs.length-1];
    }

    int length = mutableArgs.length - (throwable == null ? 0 : 1);

    immutableArgs = new Object[length];

    for (int i = 0; i < length; i++) {
      Object arg = mutableArgs[i];
      if (arg instanceof String
          || arg instanceof Boolean
          || arg instanceof BigDecimal
          || arg instanceof BigInteger
          || arg instanceof Byte
          || arg instanceof Character
          || arg instanceof Double
          || arg instanceof Float
          || arg instanceof Integer
          || arg instanceof Long
          || arg instanceof Short) {
        immutableArgs[i] = arg;
      } else if (arg instanceof AtomicInteger) {
        immutableArgs[i] = Integer.valueOf(((AtomicInteger) arg).get());
      } else if (arg instanceof AtomicLong) {
        immutableArgs[i] = Long.valueOf(((AtomicLong) arg).get());
      } else if (arg instanceof AtomicLong) {
        immutableArgs[i] = Long.valueOf(((AtomicLong) arg).get());
      } else {
        immutableArgs[i] = safeToString(arg);
      }
    }
    initialized = true;
  }

  // TODO: better conversion
  protected String safeToString(Object obj) {
    String result;
    try {
      result = obj.toString();
    } catch (Exception e) {
      try {
        result = e.toString();
      } catch (Exception f) {
        result = "exception while converting parameter to String";
      }
    }
    return result;
  }
}
