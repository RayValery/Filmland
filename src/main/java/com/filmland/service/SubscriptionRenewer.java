package com.filmland.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SubscriptionRenewer {

    private static final Logger log = LoggerFactory.getLogger(SubscriptionRenewer.class);

    @Autowired
    private SubscriptionRenewalService subscriptionRenewalService;

    @PostConstruct
    public void init(){
        Thread renewer = new Thread(this::mainLoop);
        renewer.start();
    }

    private void mainLoop() {
        while (true) {
            try {
                subscriptionRenewalService.renewOneSubscription();
            } catch (InterruptedException ex) {
                log.error("Thread has been interrupted, stop processing");
                break;
            } catch (Exception ex) {
                log.error("Failed to process renewal for a subscription", ex);
            }
        }
    }
}