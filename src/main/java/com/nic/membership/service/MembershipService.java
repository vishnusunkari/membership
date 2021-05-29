package com.nic.membership.service;

import com.nic.membership.model.Billing;
import com.nic.membership.model.Membership;
import org.springframework.stereotype.Service;

/**
 * @author vishnu.sunkari
 * @created 04/28/2021
 */

public interface MembershipService {

    Membership processMembership(Membership membership);

    Billing buildBillingMsg(Membership membership);

}
