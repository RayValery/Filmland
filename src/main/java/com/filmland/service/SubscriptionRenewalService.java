package com.filmland.service;

import com.filmland.dal.SubscriptionRepository;
import com.filmland.dal.entity.Subscription;
import java.time.LocalDate;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SubscriptionRenewalService {

    private static final Logger log = LoggerFactory.getLogger(SubscriptionRenewalService.class);
    private static final long NO_WORK_DELAY = 10_000L;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Transactional
    public void renewOneSubscription() throws InterruptedException {
        Optional<Subscription> opt = subscriptionRepository.getSubscriptionWithPaymentDateReached();
        if (opt.isEmpty()) {
            Thread.sleep(NO_WORK_DELAY);
        } else {
            renewSubscription(opt.get());
        }
    }

    private void renewSubscription(Subscription subscription) {
        LocalDate newPaymentDate = subscription.getPaymentDate().plusMonths(1);
        subscription.setPaymentDate(newPaymentDate);
        subscription.setRemainingContent(subscription.getFilmCategory().getAvailableContent());
        subscriptionRepository.save(subscription);

        log.info("The subscription fee for subscription {} was shared between all subscribers and paid. The next payment date is {}", subscription.getId(), newPaymentDate);
    }
}
