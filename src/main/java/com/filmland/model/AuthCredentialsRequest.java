package com.filmland.model;

import lombok.Data;

@Data
public class AuthCredentialsRequest {
    private String email;
    private String password;
}
