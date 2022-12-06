package com.digital.coffee_outletassessment.entities;

import com.sun.istack.NotNull;
import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;

/**
 * This is CoffeeOutlet Entity class  which is mapped with coffee_outlet database coffeeoutlet table
 */
@Data
@Entity
public class CoffeeOutlet {
    @Id
    private   int id;

    private String name;
    private String address;
    private Time opening;
    private Time closing;
    private int userid;

}
