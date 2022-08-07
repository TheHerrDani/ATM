package com.daniel.sipos.atm.exceptions;

public class AtmMoneyShortageException extends RuntimeException {

  public AtmMoneyShortageException(String message) {
    super(message);
  }

}
