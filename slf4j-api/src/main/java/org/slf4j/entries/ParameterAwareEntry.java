package org.slf4j.entries;

public interface ParameterAwareEntry extends Entry {

  Object[] getParameters();

}