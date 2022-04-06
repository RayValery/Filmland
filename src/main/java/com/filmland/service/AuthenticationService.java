package com.filmland.service;


import com.filmland.exception.CustomerAlreadyExistsException;
import com.filmland.model.AuthCredentialsRequest;
import com.filmland.auth.JwtUtil;
import com.filmland.dal.CustomerRepository;
import com.filmland.dal.entity.Customer;
import com.filmland.exception.InvalidLoginCredentialsException;
import com.filmland.model.CustomerRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Map<String, Object> registerUser(CustomerRegistrationRequest customerData){
        String encodedPass = passwordEncoder.encode(customerData.getPassword());
        Customer customer = new Customer();
        customer.setEmail(customerData.getEmail());
        customer.setPassword(encodedPass);
        try {
            customer = customerRepository.save(customer);
        } catch (DataIntegrityViolationException e){
            throw new CustomerAlreadyExistsException("Can not register user. Already registered.");
        }
        String token = jwtUtil.generateToken(customer.getEmail());
        return Collections.singletonMap("jwt-token", token);
    }

    public Map<String, Object> loginUser(AuthCredentialsRequest body){
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());

            authManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(body.getEmail());

            return Collections.singletonMap("jwt-token", token);
        } catch (AuthenticationException authExc){
            throw new InvalidLoginCredentialsException("Invalid Login Credentials");
        }
    }
}
