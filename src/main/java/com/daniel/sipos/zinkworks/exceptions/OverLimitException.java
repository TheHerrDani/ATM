package com.daniel.sipos.atm.exceptions;

public class OverLimitException extends RuntimeException {

  public OverLimitException(String message) {
    super(message);
  }

}
