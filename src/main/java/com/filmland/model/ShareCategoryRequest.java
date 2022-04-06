package com.filmland.model;

import lombok.Data;

@Data
public class ShareCategoryRequest {
    private String customerEmail;
    private String subscribedCategory;
}
