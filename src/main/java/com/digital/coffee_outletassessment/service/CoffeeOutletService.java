package com.digital.coffee_outletassessment.service;

import com.digital.coffee_outletassessment.entities.CoffeeOutlet;
import com.digital.coffee_outletassessment.repository.CoffeeOutletRepo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CoffeeOutletService is the  service class Which has getOutletListByAddress method
 */
@Service
@Slf4j
public class CoffeeOutletService {
    @Autowired
    CoffeeOutletRepo coffeeOutletRepo;

    /**
     *
     * @param address as string where the outlets are required to be searched.
     *
     * @return List<Coffee_Outlet>
     */
   public List<CoffeeOutlet> getCoffeeOutletListByAddress(String address){
       log.info("getCoffeeOutletListByAddress method called");
       log.info("address is" + address);
       List<CoffeeOutlet> outletList = coffeeOutletRepo.getCoffeeOutletListByAddress(address);
       return outletList;
   }

}
