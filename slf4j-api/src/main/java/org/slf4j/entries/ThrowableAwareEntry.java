package org.slf4j.entries;

public interface ThrowableAwareEntry extends Entry {

  Throwable getThrowable();

}
