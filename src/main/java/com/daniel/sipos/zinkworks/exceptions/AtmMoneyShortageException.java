package com.daniel.sipos.zinkworks.exceptions;

public class AtmMoneyShortageException extends RuntimeException{
  public AtmMoneyShortageException() {
    super();
  }


  public AtmMoneyShortageException(String message) {
    super(message);
  }


  public AtmMoneyShortageException(String message, Throwable cause) {
    super(message, cause);
  }
}
