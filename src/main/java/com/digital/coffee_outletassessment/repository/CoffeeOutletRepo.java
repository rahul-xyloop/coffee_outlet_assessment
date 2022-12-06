package com.digital.coffee_outletassessment.repository;

import com.digital.coffee_outletassessment.entities.CoffeeOutlet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/** CoffeeOutletRepo is the repository interface. It performs crud operation from coffeeoutlet.
 * It has getCoffeeOutletListByAddress method
 *
 */
public interface CoffeeOutletRepo extends JpaRepository<CoffeeOutlet,Integer> {
    /**
     *
     * @param address as string used to retrieve data from coffeeoutlet table
     * @type String
     * @return List<Coffee_Outlet>
     */
    @Query(value = "select *  from coffeeoutlet c where c.address like %?%1" , nativeQuery = true)
    List<CoffeeOutlet> getCoffeeOutletListByAddress(String address);

}
