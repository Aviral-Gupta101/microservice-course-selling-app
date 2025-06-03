package com.example.courseserviceauth.security.impl;

import com.example.courseserviceauth.clients.UserClient;
import com.example.courseserviceauth.entity.User;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserClient userClient;

    public UserDetailsServiceImpl(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            User foundUser = userClient.getUser(username);
            return new UserDetailsImpl(foundUser.getUsername(), foundUser.getPassword(), foundUser.getRole());
        } catch (FeignException e) {

            if(e.status() == HttpStatus.NOT_FOUND.value()) {
                throw new UsernameNotFoundException("User not found: " + username);
            }

            throw e;
        }

    }
}
