package com.filmland.service;

import com.filmland.dal.CustomerRepository;
import com.filmland.dal.FilmCategoryRepository;
import com.filmland.dal.SubscriptionRepository;
import com.filmland.dal.entity.Customer;
import com.filmland.exception.NoSuchUserException;
import com.filmland.model.AvailableCategory;
import com.filmland.model.FilmCategoryResponse;
import com.filmland.model.SubscribedCategory;
import com.filmland.model.mapper.AvailableCategoryMapper;
import com.filmland.model.mapper.SubscriptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private FilmCategoryRepository filmCategoryRepository;
    @Autowired
    private SubscriptionMapper subscriptionMapper;
    @Autowired
    private AvailableCategoryMapper availableCategoryMapper;

    public Customer getUserDetails(String email){
        return customerRepository.findByEmail(email).orElseThrow(() ->
                new NoSuchUserException("No user found with email: " + email) );
    }

    public FilmCategoryResponse getCategories(String username){
        Set<AvailableCategory> availableCategories = filmCategoryRepository.getAllAvailableCategories(username).stream()
                .map(c -> availableCategoryMapper.mapAvailableCategoryModel(c))
                .collect(Collectors.toSet());
        Set<SubscribedCategory> subscribedCategories = subscriptionRepository.getAllCustomerSubscriptions(username).stream()
                .map(s -> subscriptionMapper.mapSubscriptionModel(s))
                .collect(Collectors.toSet());
        return new FilmCategoryResponse(availableCategories, subscribedCategories);
    }
}
