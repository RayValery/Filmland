package com.filmland.model.mapper;

import com.filmland.dal.entity.Subscription;
import com.filmland.model.SubscribedCategory;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionMapper {

    public SubscribedCategory mapSubscriptionModel(Subscription entity){
        SubscribedCategory model = new SubscribedCategory();
        model.setName(entity.getFilmCategory().getName());
        model.setPrice(entity.getFilmCategory().getPrice());
        model.setStartDate(entity.getStartDate());
        model.setPaymentDate(entity.getPaymentDate());
        model.setRemainingContent(entity.getRemainingContent());
        return model;
    }
}
