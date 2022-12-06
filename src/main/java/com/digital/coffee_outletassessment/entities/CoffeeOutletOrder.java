package com.digital.coffee_outletassessment.entities;

import lombok.Data;


import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

/**
 * CoffeeoutletOrder is the Entity class which is mapped with coffee_outlet database coffeeoutletorder table
 */
@Data
@Entity

public class CoffeeOutletOrder {
    @Id
  private int id;
  private Date data;
  private Time time;
  private int userid;
}
