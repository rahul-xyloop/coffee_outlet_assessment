package com.digital.coffee_outletassessment.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *  OrderExceptionController class required for handle the exception which occurred in OrderController class
 */
@ControllerAdvice
@Slf4j
public class OrderExceptionController {
    /**
     *
     * @param exception as CustomerNotFoundException class is required for if the searched customer is not found
     * @return ResponseEntity<String>
     */
    @ExceptionHandler(value = CustomerNotFoundException.class)
    public ResponseEntity<String> exception(CustomerNotFoundException exception) {
      log.debug("customer not found "+ exception);
        return new ResponseEntity<String>("Customer not found", HttpStatus.NOT_FOUND);
    }
}


