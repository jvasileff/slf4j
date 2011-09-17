/*
 * Copyright (c) 2004-2009 QOS.ch All rights reserved.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS  IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.slf4j.cal10n;

import org.slf4j.Formatter;
import org.slf4j.Level;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.slf4j.ext.LoggerWrapper;

import ch.qos.cal10n.IMessageConveyor;

/**
 * A logger specialized in localized logging. Localization is based in the <a
 * href="http://cal10n.qos.ch">CAL10N project</p>.
 * 
 * @author Ceki G&uuml;lc&uuml;
 */
public class LocLogger extends LoggerWrapper {

  // FIXME: review serialization
  private static final long serialVersionUID = 7819373693629424495L;

  private static final String FQCN = LocLogger.class.getName();

  /**
   * Every localized message logged by a LocLogger will bear this marker. It
   * allows marker-aware implementations to perform additional processing on
   * localized messages.
   */
  static Marker LOCALIZED = MarkerFactory.getMarker("LOCALIZED");

  final IMessageConveyor imc;

  public LocLogger(Logger logger, IMessageConveyor imc) {
    super(logger);
    if (imc == null) {
      throw new IllegalArgumentException("IMessageConveyor cannot be null");
    }
    this.imc = imc;
  }

  /**
   * Log a localized message at the TRACE level.
   * 
   * @param key
   *          the key used for localization
   * @param args
   *          optional arguments
   */
  public void trace(Enum<?> key, Object... args) {
    if (!isTraceEnabled()) {
      return;
    }
    log(FQCN, LOCALIZED, Level.TRACE, new LocLoggerMessage(imc, key, args));
  }

  /**
   * Log a localized message at the DEBUG level.
   * 
   * @param key
   *          the key used for localization
   * @param args
   *          optional arguments
   */
  public void debug(Enum<?> key, Object... args) {
    if (!isDebugEnabled()) {
      return;
    }
    log(FQCN, LOCALIZED, Level.DEBUG, new LocLoggerMessage(imc, key, args));
  }

  /**
   * Log a localized message at the INFO level.
   * 
   * @param key
   *          the key used for localization
   * @param args
   *          optional arguments
   */
  public void info(Enum<?> key, Object... args) {
    if (!isInfoEnabled()) {
      return;
    }
    log(FQCN, LOCALIZED, Level.INFO, new LocLoggerMessage(imc, key, args));
  }

  /**
   * Log a localized message at the WARN level.
   * 
   * @param key
   *          the key used for localization
   * @param args
   *          optional arguments
   */
  public void warn(Enum<?> key, Object... args) {
    if (!isWarnEnabled()) {
      return;
    }
    log(FQCN, LOCALIZED, Level.WARN, new LocLoggerMessage(imc, key, args));
  }

  /**
   * Log a localized message at the ERROR level.
   * 
   * @param key
   *          the key used for localization
   * @param args
   *          optional arguments
   */
  public void error(Enum<?> key, Object... args) {
    if (!isErrorEnabled()) {
      return;
    }
    log(FQCN, LOCALIZED, Level.ERROR, new LocLoggerMessage(imc, key, args));
  }

  public LocLogger withFormatter(Formatter formatter) {
    return new LocLogger(getWrappedLogger().withFormatter(formatter), imc);
  }
}
