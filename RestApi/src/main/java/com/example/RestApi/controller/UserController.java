package com.example.RestApi.controller;


import com.example.RestApi.dto.UserDTO;
import com.example.RestApi.entity.User;
import com.example.RestApi.security.JWTUtlis;
import com.example.RestApi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtlis jwtUtlis;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping()
    public boolean health() {
        return true;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO user) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);

        List<String> roles = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        userService.loadUserByUsername(user.getUsername());
        return jwtUtlis.generateToken(user.getUsername(),roles);
    }
}
