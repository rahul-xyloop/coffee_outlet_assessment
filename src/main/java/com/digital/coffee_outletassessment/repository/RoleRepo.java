package com.digital.coffee_outletassessment.repository;

import com.digital.coffee_outletassessment.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * RoleRepo is the repository interface that is used to perform crud operation on role table
 */
public interface RoleRepo extends JpaRepository<Role,Integer> {
    /**
     *
     * @param roleId as int used to get role name from role table
     * @return
     */
    @Query(value="select name from role where id = ?1" , nativeQuery = true)
    String getRoleNameById(int roleId);

}
