package com.digital.coffee_outletassessment.repository;

import com.digital.coffee_outletassessment.dto.CustomerOrderDTO;
import com.digital.coffee_outletassessment.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * UserRepo is the repository interface. It performs crud
   operation from users table . it has getUserDetailsWithOrderDTO method.
 */
public interface UsersRepo extends JpaRepository<Users,Integer> {
    /**

     * @param userid as int used to retrieve data from users table and join with coffeeoutletorder
     *  ,order_item andmenuitem table
     * @return <List<CustomerOrderDTO>>
     */
    @Query(value = "select u.name , u.contact , c_order.order_id ,c_order.ordertime , c_order.orderdate , mi.item_name from users as u " +
            "inner join coffeeoutletorder as c_order on c_order.userid = u.id  " +
            "inner join order_item as oi on c_order.order_id = oi.orderid  " +
            "inner join menuitem as mi on oi.itemid = mi.id where u.id = ?1" ,nativeQuery = true)
    List<CustomerOrderDTO> getUserDetailsWithOrderDTO(int userid);
    @Query(value="select * from users where username = ?1" , nativeQuery = true)
    Optional<Users> findUserByUserName(String username);








}
