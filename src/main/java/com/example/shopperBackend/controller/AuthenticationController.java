package com.example.shopperBackend.controller;

import com.example.shopperBackend.model.AuthenticationRequest;
import com.example.shopperBackend.model.AuthenticationResponse;
import com.example.shopperBackend.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            AuthenticationResponse authResponse = authenticationService.createAuthenticationToken(authenticationRequest);
            return ResponseEntity.ok()
                    .body(authResponse);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Incorrect Username Or Password");
        }
    }
}



