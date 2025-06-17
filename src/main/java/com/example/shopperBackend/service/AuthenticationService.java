package com.example.shopperBackend.service;

import com.example.shopperBackend.security.CustomUserDetailsService;
import com.example.shopperBackend.model.AuthenticationRequest;
import com.example.shopperBackend.model.AuthenticationResponse;
import com.example.shopperBackend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private CustomUserDetailsService  customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthenticationResponse createAuthenticationToken(AuthenticationRequest authenticationRequest) throws Exception {
        try {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            if (!passwordEncoder.matches(authenticationRequest.getPassword(), userDetails.getPassword())) {
                throw new BadCredentialsException("Incorrect password");
            }
            return new AuthenticationResponse(jwtUtil.generateToken(userDetails));
        } catch (Exception e) {
            throw e;
        }

    }
}





