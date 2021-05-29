package com.nic.membership.controller;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.nic.membership.model.Billing;
import com.nic.membership.model.Membership;
import com.nic.membership.service.MembershipService;
import com.nic.membership.serviceLocation.ServiceLocationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author vishnu.sunkari
 * @created 04/27/2021
 */
@RestController
@XRayEnabled
@Slf4j
public class MembershipController {

    @Autowired
    private RestTemplate restTemplate;

    //@Value("${BILLING_URI:http://billing.nic-qa.com/billing}")
    /*@Value("${billing.uri: }")
    private String billingUri;*/

    /*@Autowired
    private Environment env;*/

    @Autowired
    private ServiceLocationResolver serviceLocationResolver;

    @Autowired
    MembershipService membershipService;

    @PostMapping(path = "/v1/process", consumes = "application/json", produces="application/json")
    public Billing processMembership(@RequestBody Membership membershipMsg) {

        log.info("MembershipController.processMembership STARTT");
        /*
         *  When deployed to AWS, this billngUri should be resolved to a valid AWS url
         *   On local this will be resolved to localhost:8010
         */
        String billingUri = serviceLocationResolver.resolve();
        log.info("billingUri : " + billingUri);

        membershipMsg = membershipService.processMembership(membershipMsg);
        Billing billingMsg = membershipService.buildBillingMsg(membershipMsg);
        ResponseEntity<Billing> responseEntity = restTemplate.postForEntity(
                "http://" + billingUri + "/billing/v1/process",
                new HttpEntity<Billing>(billingMsg), Billing.class);

        Billing billingResponse = responseEntity.getBody();
        if(responseEntity.getStatusCode() == HttpStatus.OK) {
            membershipMsg.setMembershipStatus("Active");
            //Persist membershipMsg in Database
        }
        //Persist billingMsg in Database
        return billingResponse;
    }

}
