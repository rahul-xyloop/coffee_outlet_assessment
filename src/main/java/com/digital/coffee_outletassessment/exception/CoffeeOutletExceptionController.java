package com.digital.coffee_outletassessment.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * CoffeeOutletExceptionController class required for handle the exception which occurred in CoffeeOutletController class
 */
@ControllerAdvice
@Slf4j
public class CoffeeOutletExceptionController {
    /**
     *
     * @param exception as AddressNotFoundException class is required for if the searched address is not found
     * @return ResponseEntity<String>
     */
    @ExceptionHandler(value = AddressNotFoundException.class)
    public ResponseEntity<String> exception(AddressNotFoundException exception) {
        log.debug("Address not found " + exception);
        return new ResponseEntity<String>("Address not found", HttpStatus.NOT_FOUND);
    }
}
