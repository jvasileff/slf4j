package org.slf4j.messages;

import org.slf4j.helpers.StringUtils;

import java.util.Arrays;

/**
 * This Message implements the default parameterized message format of SLF4J.
 * 
 * @author Joern Huxhorn
 * @author John Vasileff
 */
public class DefaultMessage implements FormattedMessage, ParameterizedMessage,
    ThrowableMessage {

  private static final long serialVersionUID = -665975803997290697L;

  private String messagePattern;
  private transient String formattedMessage;
  private String[] parameters;
  private transient Throwable throwable;

  public DefaultMessage() {
    this(null, null, null);
  }

  public DefaultMessage(String messagePattern, String[] parameters, Throwable throwable) {
    this.messagePattern = messagePattern;
    this.parameters = parameters;
    this.throwable = throwable;
  }

  public String getFormattedMessage() {
    if (formattedMessage == null) {
      formattedMessage = format(messagePattern, parameters);
    }
    return formattedMessage;
  }

  public String getMessagePattern() {
    return messagePattern;
  }

  public String[] getParameters() {
    return parameters;
  }

  /**
   * Returns the Throwable that was given as the last argument, if any.
   * It will not survive serialization.
   *
   * @return the Throwable, if any.
   */
  public Throwable getThrowable() {
    return throwable;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    DefaultMessage that = (DefaultMessage) o;

    if (messagePattern != null ? !messagePattern.equals(that.messagePattern) : that.messagePattern != null) {
      return false;
    }
    if (!Arrays.equals(parameters, that.parameters)) return false;
    //if (throwable != null ? !throwable.equals(that.throwable) : that.throwable != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = messagePattern != null ? messagePattern.hashCode() : 0;
    result = 31 * result + (parameters != null ? Arrays.hashCode(parameters) : 0);
    return result;
  }

  private static final char DELIM_START = '{';
  private static final char DELIM_STOP = '}';
  private static final char ESCAPE_CHAR = '\\';

  /**
   * Replace placeholders in the given messagePattern with arguments.
   *
   * @param messagePattern the message pattern containing placeholders.
   * @param arguments      the arguments to be used to replace placeholders.
   * @return the formatted message.
   */
  public static String format(String messagePattern, String... arguments) {
    if (messagePattern == null || arguments == null || arguments.length == 0) {
      return messagePattern;
    }

    StringBuilder result = new StringBuilder();
    int escapeCounter = 0;
    int currentArgument = 0;
    for (int i = 0; i < messagePattern.length(); i++) {
      char curChar = messagePattern.charAt(i);
      if (curChar == ESCAPE_CHAR) {
        escapeCounter++;
      }
      else {
        if (curChar == DELIM_START) {
          if (i < messagePattern.length() - 1) {
            if (messagePattern.charAt(i + 1) == DELIM_STOP) {
              // write escaped escape chars
              int escapedEscapes = escapeCounter / 2;
              for (int j = 0; j < escapedEscapes; j++) {
                result.append(ESCAPE_CHAR);
              }

              if (escapeCounter % 2 == 1) {
                // i.e. escaped
                // write escaped escape chars
                result.append(DELIM_START);
                result.append(DELIM_STOP);
              }
              else {
                // unescaped
                if (currentArgument < arguments.length) {
                  result.append(arguments[currentArgument]);
                }
                else {
                  result.append(DELIM_START).append(DELIM_STOP);
                }
                currentArgument++;
              }
              i++;
              escapeCounter = 0;
              continue;
            }
          }
        }
        // any other char beside ESCAPE or DELIM_START/STOP-combo
        // write unescaped escape chars
        if (escapeCounter > 0) {
          for (int j = 0; j < escapeCounter; j++) {
            result.append(ESCAPE_CHAR);
          }
          escapeCounter = 0;
        }
        result.append(curChar);
      }
    }
    return result.toString();
  }

  /**
   * Counts the number of unescaped placeholders in the given messagePattern.
   *
   * @param messagePattern the message pattern to be analyzed.
   * @return the number of unescaped placeholders.
   */
  public static int countArgumentPlaceholders(String messagePattern) {
    if (messagePattern == null) {
      return 0;
    }

    int delim = messagePattern.indexOf(DELIM_START);

    if (delim == -1) {
      // special case, no placeholders at all.
      return 0;
    }
    int result = 0;
    boolean isEscaped = false;
    for (int i = 0; i < messagePattern.length(); i++) {
      char curChar = messagePattern.charAt(i);
      if (curChar == ESCAPE_CHAR) {
        isEscaped = !isEscaped;
      }
      else if (curChar == DELIM_START) {
        if (!isEscaped) {
          if (i < messagePattern.length() - 1) {
            if (messagePattern.charAt(i + 1) == DELIM_STOP) {
              result++;
              i++;
            }
          }
        }
        isEscaped = false;
      }
      else {
        isEscaped = false;
      }
    }
    return result;
  }

  /**
   * <p>This constructor returns a ParameterizedMessage which contains the arguments converted to String
   * as well as an optional Throwable.</p>
   * <p/>
   * <p>If the last argument is a Throwable and is NOT used up by a placeholder in the message pattern it is returned
   * in ParameterizedMessage.getThrowable() and won't be contained in the created String[].<br/>
   * If it is used up ParameterizedMessage.getThrowable() will return null even if the last argument was a Throwable!</p>
   *
   * @param messagePattern the message pattern that to be checked for placeholders.
   * @param arguments      the argument array to be converted.
   * @return a ParameterizedMessage containing the messagePattern, converted arguments and, optionally, a Throwable.
   */
  public DefaultMessage(String messagePattern, Object... arguments) {
    if (arguments == null) {
      this.messagePattern = messagePattern;
      return;
    }

    // placeholders are always counted because the amount is needed for both
    // the handling of Throwable as the last argument (http://bugzilla.slf4j.org/show_bug.cgi?id=70)
    // and the handling of the special case (http://jira.qos.ch/browse/LBGENERAL-36) below
    int argsCount = countArgumentPlaceholders(messagePattern);
    int resultArgCount = arguments.length;
    Throwable throwable = null;
    if (argsCount < arguments.length) {
      if (arguments[arguments.length - 1] instanceof Throwable) {
        throwable = (Throwable) arguments[arguments.length - 1];
        resultArgCount--;
      }
    }

    String[] stringArgs;
    if (argsCount == 1 && throwable == null && arguments.length > 1) {
      // special case
      // The pattern contains exactly one placeholder, there was no Throwable detected and
      // more than one arguments exists.
      // This means that the whole array shall be used as the replacement.
      // http://jira.qos.ch/browse/LBGENERAL-36
      // ("{}", new String[]{"Foo", "Bar"}) => [Foo, Bar] instead of Foo
      stringArgs = new String[1];
      stringArgs[0] = StringUtils.deepToString(arguments);
    }
    else {
      stringArgs = new String[resultArgCount];
      for (int i = 0; i < stringArgs.length; i++) {
        stringArgs[i] = StringUtils.deepToString(arguments[i]);
      }
    }
    
    this.messagePattern = messagePattern;
    this.parameters = stringArgs;
    this.throwable = throwable;
  }

  @Override
  public String toString() {
    return "ParameterizedMessage[messagePattern=" + messagePattern + ", parameters=" + Arrays.toString(parameters) + ", throwable=" + throwable + "]";
  }

  public DefaultMessage clone() throws CloneNotSupportedException {
    DefaultMessage result = (DefaultMessage) super.clone();
    if (parameters != null) {
      result.parameters = new String[parameters.length];
      System.arraycopy(parameters, 0, result.parameters, 0, parameters.length);
    }
    // Throwable is not cloned.
    return result;
  }

}
