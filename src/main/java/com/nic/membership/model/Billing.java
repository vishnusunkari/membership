package com.nic.membership.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author vishnu.sunkari
 * @created 04/28/2021
 */
@Data
public class Billing {
    private Integer billingId;
    private String billingStatus;
    private String billedDate;
    private BigDecimal billingAmount;
}
