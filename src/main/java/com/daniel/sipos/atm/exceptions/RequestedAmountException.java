package com.daniel.sipos.atm.exceptions;

public class RequestedAmountException extends RuntimeException {

  public RequestedAmountException(String message) {
    super(message);
  }

}
