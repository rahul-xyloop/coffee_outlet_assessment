package com.digital.coffee_outletassessment.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class UserRole {
    @Id
    private int id ;
    @Column(name ="userid")
    private int userId;
    @Column(name = "roleid")
    private int roleId;
}
