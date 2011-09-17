package org.slf4j.messages;

public interface ThrowableMessage extends Message {

  Throwable getThrowable();

}
