package com.daniel.sipos.zinkworks.exceptions;

public class OverLimitException extends RuntimeException {
  public OverLimitException() {
    super();
  }


  public OverLimitException(String message) {
    super(message);
  }


  public OverLimitException(String message, Throwable cause) {
    super(message, cause);
  }
}
