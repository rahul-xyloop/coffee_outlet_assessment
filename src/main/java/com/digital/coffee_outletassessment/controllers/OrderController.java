package com.digital.coffee_outletassessment.controllers;

import com.digital.coffee_outletassessment.dto.CustomerOrderDTO;
import com.digital.coffee_outletassessment.exception.CustomerNotFoundException;
import com.digital.coffee_outletassessment.service.OrderService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;


/**
 * This is OrderController controller class. It has api method listOrderBYCustomer
 */
@RestController
@RequestMapping("customer")
@Slf4j
@Validated
public class OrderController {
    @Autowired
    private OrderService orderService;
    /**
     *
     * @param customerId as int where orders,menu-items with customer detail required to be search
     * @return ResponceEntitu<List<CustomerOrderDTO>>
     */
    @RequestMapping("order/{customerId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')" )
     ResponseEntity<List<CustomerOrderDTO>> listOrderByCustomer(@PathVariable int customerId ) {

        List<CustomerOrderDTO> customerOrderDTOList = orderService.viewOrders(customerId);
        log.debug("Request Customer id {}", customerId);
        /*
        it's checking if customer is not found on the basis of customer id than it throw CustomerNotFound Exception
         */
        if (customerOrderDTOList.isEmpty()) {
            log.info("customerOrderDTOList is empty");
          throw new CustomerNotFoundException();
        }
        return new ResponseEntity<List<CustomerOrderDTO>>(customerOrderDTOList, HttpStatus.OK);


    }


}
