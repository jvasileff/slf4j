/* 
 * Copyright (c) 2004-2011 QOS.ch
 * All rights reserved.
 * 
 * Permission is hereby granted, free  of charge, to any person obtaining
 * a  copy  of this  software  and  associated  documentation files  (the
 * "Software"), to  deal in  the Software without  restriction, including
 * without limitation  the rights to  use, copy, modify,  merge, publish,
 * distribute,  sublicense, and/or sell  copies of  the Software,  and to
 * permit persons to whom the Software  is furnished to do so, subject to
 * the following conditions:
 * 
 * The  above  copyright  notice  and  this permission  notice  shall  be
 * included in all copies or substantial portions of the Software.
 * 
 * THE  SOFTWARE IS  PROVIDED  "AS  IS", WITHOUT  WARRANTY  OF ANY  KIND,
 * EXPRESS OR  IMPLIED, INCLUDING  BUT NOT LIMITED  TO THE  WARRANTIES OF
 * MERCHANTABILITY,    FITNESS    FOR    A   PARTICULAR    PURPOSE    AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE,  ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.slf4j.ext;

import java.io.ObjectStreamException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.messages.Message;
import org.slf4j.messages.SimpleMessage;

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
