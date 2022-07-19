package com.daniel.sipos.zinkworks.exceptions;

public class AtmDenominationException extends RuntimeException {
  public AtmDenominationException() {
    super();
  }


  public AtmDenominationException(String message) {
    super(message);
  }


  public AtmDenominationException(String message, Throwable cause) {
    super(message, cause);
  }
}
