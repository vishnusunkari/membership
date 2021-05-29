package com.nic.membership.service;

import com.nic.membership.model.Billing;
import com.nic.membership.model.Membership;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @created 05/01/2021
 * @author vishnu.sunkari
 */
class MembershipServiceImplTest  {
    MembershipService membershipService = new MembershipServiceImpl();
    Membership membership = getMemberhsipToProcess();

    @Test
    @DisplayName("processMembershipTest : adding discount to membership")
    void processMembershipTest() {
        assertEquals(new BigDecimal(10), membershipService.processMembership(membership).getDiscountedAmount(), "Discount Applied should equal 10");
    }

    Membership getMemberhsipToProcess (){
        Membership membership = new Membership();
        membership.setCustomerId(1000);
        membership.setCustomerName("abc");
        membership.setDateStarted("2020-11-11");
        membership.setMembershipId(2000);
        membership.setMembershipStatus("Pending");
        membership.setOfferId(10);
        return membership;
    }

}