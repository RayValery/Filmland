package com.filmland.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class FilmCategoryResponse {
    private Set<AvailableCategory> availableCategories;
    private Set<SubscribedCategory> subscribedCategories;
}
