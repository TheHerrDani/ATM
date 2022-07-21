package com.daniel.sipos.zinkworks.controller.controlleradvice;

import com.daniel.sipos.zinkworks.exceptions.AtmDenominationException;
import com.daniel.sipos.zinkworks.exceptions.AtmMoneyShortageException;
import com.daniel.sipos.zinkworks.exceptions.OverLimitException;
import com.daniel.sipos.zinkworks.exceptions.RequestedAmountException;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  public static final String GENERIC_ERROR = "Something went wrong in the application";

  @ExceptionHandler(NullPointerException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ResponseEntity<String> handleNullPointerException(RuntimeException ex) {
    return new ResponseEntity<>(GENERIC_ERROR, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> handleNoSuchElementException(RuntimeException ex) {
    return new ResponseEntity<>(GENERIC_ERROR, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(AtmDenominationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleAtmDenominationException(RuntimeException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AtmMoneyShortageException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleAtmMoneyShortageException(RuntimeException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(OverLimitException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleOverLimitException(RuntimeException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RequestedAmountException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleRequestedAmountException(RuntimeException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

}
