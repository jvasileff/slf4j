package org.slf4j.ext;

import java.io.ObjectStreamException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.SimpleMessage;
import org.slf4j.messages.Message;

public class JUFLoggerFactory {

  public static Logger getLogger(String name, final Locale locale) {
    return new LoggerWrapper(LoggerFactory.getLogger(name)) {

      private static final long serialVersionUID = 8956351052999390045L;

      @Override
      protected Message newMessage(String format, Object... args) {
        if (args == null || args.length == 0) {
          return new SimpleMessage(format);
        } else {
          return new JUFMessage(locale, format, args);
        }
      }

      @Override
      protected Object readResolve() throws ObjectStreamException {
        return JUFLoggerFactory.getLogger(getName(), locale);
      }
    };
  }

  public static Logger getLogger(String name) {
    return getLogger(name, null);
  }

  public static Logger getLogger(Class<?> clazz, Locale locale) {
    return getLogger(clazz.getName(), locale);
  }

  public static Logger getLogger(Class<?> clazz) {
    return getLogger(clazz, null);
  }
}
