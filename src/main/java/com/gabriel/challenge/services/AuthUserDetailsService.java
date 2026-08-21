package com.gabriel.challenge.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gabriel.challenge.domain.users.UsersRepository;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    AuthUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

}
