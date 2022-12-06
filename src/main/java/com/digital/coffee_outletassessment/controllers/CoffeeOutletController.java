package com.digital.coffee_outletassessment.controllers;

import com.digital.coffee_outletassessment.entities.CoffeeOutlet;
import com.digital.coffee_outletassessment.exception.AddressNotFoundException;
import com.digital.coffee_outletassessment.service.CoffeeOutletService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is CoffeeOutletController Class. it has coffeeOutletList api method.
 */
@RestController
@RequestMapping("outlet")
@Slf4j
@Validated

public class CoffeeOutletController {
    @Autowired
    private CoffeeOutletService coffeeOutletService;

    /**
     * The method returns the list of coffee outlets.
     *
     * @param address as string where the outlets are required to be searched.
     * @return ResponseEntity<List < Coffee_Outlet>>
     */
    @GetMapping("list/{address}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    ResponseEntity<List<CoffeeOutlet>> coffeeOutletList(@PathVariable String address) {
        log.info("coffeeOutletList is called");
        List<CoffeeOutlet> list = coffeeOutletService.getCoffeeOutletListByAddress(address);
        log.debug("Request address {} ", address);
        /*
        it's checking if coffee_outlet is not found on the basis of address than it throw AddressNotFound Exception
         */
        if (list.isEmpty()) {
            log.info("list is Empty");
            throw new AddressNotFoundException();
        }
        return new ResponseEntity<List<CoffeeOutlet>>(list, HttpStatus.OK);
    }

}
