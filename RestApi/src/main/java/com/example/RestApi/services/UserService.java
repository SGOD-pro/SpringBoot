package com.example.RestApi.services;

import com.example.RestApi.dto.UserDTO;
import com.example.RestApi.entity.User;
import com.example.RestApi.repository.UserRepository;
import com.example.RestApi.security.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    public User register(User user){
        user.setPassword(new BCryptPasswordEncoder(10).encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findByUsername(username);
        if(user.isEmpty()) throw new UsernameNotFoundException("User not found.!");
        return new UserPrinciple(user.get());
    }

}
