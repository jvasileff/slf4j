package org.slf4j.messages;

public interface ParameterizedMessage extends Message {

  Object[] getParameters();

}