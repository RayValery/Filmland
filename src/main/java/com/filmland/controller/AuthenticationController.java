package com.filmland.controller;

import com.filmland.model.AuthCredentialsRequest;
import com.filmland.model.CustomerRegistrationRequest;
import com.filmland.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public Map<String, Object> registerUser(@RequestBody CustomerRegistrationRequest customerData){
        return authenticationService.registerUser(customerData);
    }

    @PostMapping("/login")
    public Map<String, Object> loginUser(@RequestBody AuthCredentialsRequest credentials){
        return authenticationService.loginUser(credentials);
    }
}
