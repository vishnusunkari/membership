package com.nic.membership.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author vishnu.sunkari
 * @created 04/27/2021
 */

@Data
public class Membership {

    private Integer membershipId;
    private Integer offerId;
    private Integer customerId;
    private String customerName;
    private String membershipStatus;
    private String dateStarted;
    private String dateExpiration;
    private BigDecimal discountedAmount;
    private BigDecimal monthlyBillingAmount;

}
