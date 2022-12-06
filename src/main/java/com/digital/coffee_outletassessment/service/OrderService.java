package com.digital.coffee_outletassessment.service;

import com.digital.coffee_outletassessment.dto.CustomerOrderDTO;
import com.digital.coffee_outletassessment.repository.UsersRepo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This is OrderService service class . Which has viewOrder method
 */
@Service
@Slf4j
public class OrderService {
    @Autowired
    UsersRepo usersRepo;


    /**
     *
     * @param customerId  as int where orders,menu-items with customer detail required to be search
     * @return List<CustomerOrderList>
     */
    public List<CustomerOrderDTO> viewOrders(int customerId){

    List<CustomerOrderDTO> orderCustomerOrderDTOList = usersRepo.getUserDetailsWithOrderDTO(customerId);
    log.info("viewOrder method called.");
    log.debug("Customer id is "+ customerId);

    return orderCustomerOrderDTOList;



    }


}
