package com.filmland.controller;

import com.filmland.auth.AuthenticationUtil;
import com.filmland.model.FilmCategoryResponse;
import com.filmland.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private AuthenticationUtil authenticationUtil;

    @GetMapping("/categories")
    public FilmCategoryResponse getCategories(){
        String username = authenticationUtil.getAuthenticatedUserEmail();
        return customerService.getCategories(username);
    }
}
