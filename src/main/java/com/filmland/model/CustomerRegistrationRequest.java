package com.filmland.model;

import lombok.Data;

@Data
public class CustomerRegistrationRequest {
    private String email;
    private String password;
}
