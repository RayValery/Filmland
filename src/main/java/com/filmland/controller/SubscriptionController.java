package com.filmland.controller;

import com.filmland.model.ShareCategoryRequest;
import com.filmland.model.SubscribeCategoryRequest;
import com.filmland.model.SubscribedCategory;
import com.filmland.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/subscribe")
    public SubscribedCategory subscribeUserToCategory(@RequestBody SubscribeCategoryRequest request){
        return subscriptionService.subscribe(request.getAvailableCategory());
    }

    @PostMapping("/share")
    public SubscribedCategory shareSubscribedCategoryWithCustomer(@RequestBody ShareCategoryRequest request){
        return subscriptionService.shareSubscription(request.getCustomerEmail(), request.getSubscribedCategory());
    }
}
