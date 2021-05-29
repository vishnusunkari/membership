package com.nic.membership.service;

import com.nic.membership.model.Billing;
import com.nic.membership.model.Membership;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author vishnu.sunkari
 * @created 04/28/2021
 */
@Service
public class MembershipServiceImpl implements MembershipService {

    @Override
    public Membership processMembership(Membership membership) {
        membership.setDiscountedAmount(new BigDecimal(10));
        return membership;
    }

    @Override
    public Billing buildBillingMsg(Membership membership) {
        Billing billingMsg = new Billing();
        billingMsg.setBillingStatus("pending");
        billingMsg.setBillingId(10);
        BigDecimal billingAmount = membership.getMonthlyBillingAmount().subtract(membership.getDiscountedAmount());
        billingMsg.setBillingAmount(billingAmount);
        return billingMsg;
    }
}
