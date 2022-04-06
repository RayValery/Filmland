package com.filmland.service;

import com.filmland.auth.AuthenticationUtil;
import com.filmland.dal.FilmCategoryRepository;
import com.filmland.dal.SubscriptionRepository;
import com.filmland.dal.entity.Customer;
import com.filmland.dal.entity.FilmCategory;
import com.filmland.dal.entity.Subscription;
import com.filmland.exception.AlreadySubscribedCategoryException;
import com.filmland.exception.NoSuchCategoryException;
import com.filmland.exception.NoSuchSubscriptionException;
import com.filmland.model.SubscribedCategory;
import com.filmland.model.mapper.SubscriptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SubscriptionService {

    private static final Integer PAYMENT_DELAY_IN_MONTHS = 1;

    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private FilmCategoryRepository filmCategoryRepository;
    @Autowired
    private SubscriptionMapper subscriptionMapper;
    @Autowired
    private AuthenticationUtil authenticationUtil;

    public SubscribedCategory subscribe(String categoryName){
        FilmCategory filmCategory = filmCategoryRepository.getFirstByName(categoryName).orElseThrow(() ->
                new NoSuchCategoryException("No category was found with name: " + categoryName));

        Customer authenticatedCustomer = customerService.getUserDetails(authenticationUtil.getAuthenticatedUserEmail());
        boolean isAlreadySubscribed = authenticatedCustomer.getSubscriptions().stream().map(Subscription::getFilmCategory)
                .anyMatch(category -> category.getName().equals(categoryName));
        if (isAlreadySubscribed) {
            throw new AlreadySubscribedCategoryException("Customer is already subscribed to category: " + categoryName);
        }

        Subscription subscription = new Subscription();
        subscription.addCustomer(authenticatedCustomer);
        subscription.setStartDate(LocalDate.now());
        subscription.setPaymentDate(subscription.getStartDate().plusMonths(PAYMENT_DELAY_IN_MONTHS));
        subscription.setFilmCategory(filmCategory);
        subscription.setRemainingContent(filmCategory.getAvailableContent());

        subscription = subscriptionRepository.save(subscription);
        return subscriptionMapper.mapSubscriptionModel(subscription);
    }

    public SubscribedCategory shareSubscription(String customerEmail, String subscribedCategory){
        Subscription subscription = subscriptionRepository.getSubscriptionByFilmCategory(authenticationUtil.getAuthenticatedUserEmail(),
                subscribedCategory).orElseThrow(() -> new NoSuchSubscriptionException("No subscription found for category " + subscribedCategory));
        subscription.addCustomer(customerService.getUserDetails(customerEmail));
        subscription = subscriptionRepository.save(subscription);
        return subscriptionMapper.mapSubscriptionModel(subscription);
    }
}
