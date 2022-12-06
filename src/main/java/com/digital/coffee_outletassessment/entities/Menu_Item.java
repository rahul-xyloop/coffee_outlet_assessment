package com.digital.coffee_outletassessment.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Menu_Item is the Entity class which is mapped with coffee_outlet database menu_item table
 *
 */
@Data
@Entity
public class Menu_Item {
    @Id
    private int id;
    private String name;
    private float price;
    private int menuId;

}
