package com.digital.coffee_outletassessment.dto;

import java.sql.Time;
import java.util.Date;

/**
 * This is the Data Transfer Object interface which
   hold the returned object of getUserDetailsWithOrder method
 */
public interface CustomerOrderDTO {
    String getName();
    String getContact();
    int getOrder_Id();
    Time getOrderTime();
    Date getOrderDate();
    String getItem_Name();
}
