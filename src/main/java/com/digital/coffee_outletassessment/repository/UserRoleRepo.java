package com.digital.coffee_outletassessment.repository;

import com.digital.coffee_outletassessment.entities.UserRole;
import liquibase.pro.packaged.U;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository interface that performs crud operation on user_role table
 */
public interface UserRoleRepo extends JpaRepository<UserRole,Integer> {

    /**
     *
     * @param userId as int that is used to fletch data from user_role table.
     * @return List<UserRole> return list of user_role
     */
   @Query(value = "select * from user_role where userid = ?1" , nativeQuery = true)
    public List<UserRole> getUserRole(int userId);

}
