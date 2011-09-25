// portions copied from the huxi/slf4j-redesign branch

package org.slf4j.messages;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class MessageUtils {

  public static final String RECURSION_PREFIX = "[...";
  public static final String RECURSION_SUFFIX = "...]";
  public static final String ERROR_PREFIX = "[!!!";
  public static final String ERROR_SEPARATOR = "=>";
  public static final String ERROR_MSG_SEPARATOR = ":";
  public static final String ERROR_SUFFIX = "!!!]";

  private MessageUtils() {
    // no instances;
  }

  /**
   * This method performs a deep toString of the given Object.
   * Primitive arrays are converted using their respective Arrays.toString methods while
   * special handling is implemented for "container types", i.e. Object[], Map and Collection because those could
   * contain themselves.
   * <p/>
   * dejaVu is used in case of those container types to prevent an endless recursion.
   * <p/>
   * It should be noted that neither AbstractMap.toString() nor AbstractCollection.toString() implement such a behavior.
   * They only check if the container is directly contained in itself, but not if a contained container contains the
   * original one. Because of that, Arrays.toString(Object[]) isn't safe either.
   * Confusing? Just read the last paragraph again and check the respective toString() implementation.
   * <p/>
   * This means, in effect, that logging would produce a usable output even if an ordinary System.out.println(o)
   * would produce a relatively hard-to-debug StackOverflowError.
   *
   * @param o      the Object to convert into a String
   * @param str    the StringBuilder that o will be appended to
   * @param dejaVu a list of container identities that were already used.
   */
  public static void recursiveDeepToString(Object o, StringBuilder str, Set<String> dejaVu) {
    if (o == null) {
      str.append("null");
      return;
    }
    if (o instanceof String) {
      str.append(o);
      return;
    }
  
    Class<?> oClass = o.getClass();
    if (oClass.isArray()) {
      if (oClass == byte[].class) {
        str.append(Arrays.toString((byte[]) o));
      }
      else if (oClass == short[].class) {
        str.append(Arrays.toString((short[]) o));
      }
      else if (oClass == int[].class) {
        str.append(Arrays.toString((int[]) o));
      }
      else if (oClass == long[].class) {
        str.append(Arrays.toString((long[]) o));
      }
      else if (oClass == float[].class) {
        str.append(Arrays.toString((float[]) o));
      }
      else if (oClass == double[].class) {
        str.append(Arrays.toString((double[]) o));
      }
      else if (oClass == boolean[].class) {
        str.append(Arrays.toString((boolean[]) o));
      }
      else if (oClass == char[].class) {
        str.append(Arrays.toString((char[]) o));
      }
      else {
        // special handling of container Object[]
        String id = MessageUtils.identityToString(o);
        if (dejaVu.contains(id)) {
          str.append(RECURSION_PREFIX).append(id).append(RECURSION_SUFFIX);
        }
        else {
          dejaVu.add(id);
          Object[] oArray = (Object[]) o;
          str.append("[");
          boolean first = true;
          for (Object current : oArray) {
            if (first) {
              first = false;
            }
            else {
              str.append(", ");
            }
            recursiveDeepToString(current, str, new HashSet<String>(dejaVu));
          }
          str.append("]");
        }
        //str.append(Arrays.deepToString((Object[]) o));
      }
    }
    else if (o instanceof Map) {
      // special handling of container Map
      String id = MessageUtils.identityToString(o);
      if (dejaVu.contains(id)) {
        str.append(RECURSION_PREFIX).append(id).append(RECURSION_SUFFIX);
      }
      else {
        dejaVu.add(id);
        Map<?, ?> oMap = (Map<?, ?>) o;
        str.append("{");
        boolean isFirst = true;
        for (Map.Entry<?, ?> current : oMap.entrySet()) {
          if (isFirst) {
            isFirst = false;
          }
          else {
            str.append(", ");
          }
          Object key = current.getKey();
          Object value = current.getValue();
          recursiveDeepToString(key, str, new HashSet<String>(dejaVu));
          str.append("=");
          recursiveDeepToString(value, str, new HashSet<String>(dejaVu));
        }
        str.append("}");
      }
    }
    else if (o instanceof Collection) {
      // special handling of container Collection
      String id = MessageUtils.identityToString(o);
      if (dejaVu.contains(id)) {
        str.append(RECURSION_PREFIX).append(id).append(RECURSION_SUFFIX);
      }
      else {
        dejaVu.add(id);
        Collection<?> oCol = (Collection<?>) o;
        str.append("[");
        boolean isFirst = true;
        for (Object current : oCol) {
          if (isFirst) {
            isFirst = false;
          }
          else {
            str.append(", ");
          }
          recursiveDeepToString(current, str, new HashSet<String>(dejaVu));
        }
        str.append("]");
      }
    }
    else if (o instanceof Date) {
      Date date = (Date) o;
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
      // I'll leave it like this for the moment... this could probably be optimized using ThreadLocal...
      str.append(format.format(date));
    }
    else {
      // it's just some other Object, we can only use toString().
      try {
        str.append(o.toString());
      }
      catch (Throwable t) {
        str.append(ERROR_PREFIX);
        str.append(MessageUtils.identityToString(o));
        str.append(ERROR_SEPARATOR);
        String msg = t.getMessage();
        String className = t.getClass().getName();
        str.append(className);
        if (!className.equals(msg)) {
          str.append(ERROR_MSG_SEPARATOR);
          str.append(msg);
        }
        str.append(ERROR_SUFFIX);
      }
    }
  }

  /**
   * This method returns the same as if Object.toString() would not have been
   * overridden in obj.
   * <p/>
   * Note that this isn't 100% secure as collisions can always happen with hash codes.
   * <p/>
   * Copied from Object.hashCode():
   * As much as is reasonably practical, the hashCode method defined by
   * class <tt>Object</tt> does return distinct integers for distinct
   * objects. (This is typically implemented by converting the internal
   * address of the object into an integer, but this implementation
   * technique is not required by the
   * Java<font size="-2"><sup>TM</sup></font> programming language.)
   *
   * @param obj the Object that is to be converted into an identity string.
   * @return the identity string as also defined in Object.toString()
   */
  public static String identityToString(Object obj) {
    if (obj == null) {
      return null;
    }
    return obj.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(obj));
  }

  public static String deepToString(Object o) {
    if (o == null) {
      return null;
    }
    if (o instanceof String) {
      return (String) o;
    }
    StringBuilder str = new StringBuilder();
    Set<String> dejaVu = new HashSet<String>(); // that's actually a neat name ;)
    recursiveDeepToString(o, str, dejaVu);
    return str.toString();
  }
  
  public static Object[] newArrayOfImmutables(Object[] args, boolean trimLast) {
    if (args == null || args.length == 0 || (args.length == 1 && trimLast)) {
      return null;
    }

    int length = args.length - (trimLast ? 1 : 0);
    Object[] immutableArgs = new Object[length];
    
    for (int i = 0; i < length; i++) {
      Object arg = args[i];
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
        immutableArgs[i] = MessageUtils.deepToString(arg);
      }
    }
    return immutableArgs;
  }
}
