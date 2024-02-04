package com.nihat.flightsearchapi.services;

import com.nihat.flightsearchapi.entities.AppUser;
import com.nihat.flightsearchapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//@Service --->  // Created with @Bean in SecurityConfig.java
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            AppUser user = optionalUser.get();
            return user;
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
