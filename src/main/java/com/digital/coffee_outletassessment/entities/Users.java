package com.digital.coffee_outletassessment.entities;

import lombok.Data;
import javax.persistence.*;

/**
 * Users Entity class which mapped with coffee_outlet database users table
 *
 */
@Entity
@Data
public class Users {
    @Id
  private  int id;
  private  String name;
  private  String contact;
  private String username;
  private String password;
}
