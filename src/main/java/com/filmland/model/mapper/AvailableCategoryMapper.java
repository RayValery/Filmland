package com.filmland.model.mapper;

import com.filmland.dal.entity.FilmCategory;
import com.filmland.model.AvailableCategory;
import org.springframework.stereotype.Component;

@Component
public class AvailableCategoryMapper {

    public AvailableCategory mapAvailableCategoryModel(FilmCategory entity){
        AvailableCategory model = new AvailableCategory();
        model.setName(entity.getName());
        model.setPrice(entity.getPrice());
        model.setAvailableContent(entity.getAvailableContent());
        return model;
    }
}
