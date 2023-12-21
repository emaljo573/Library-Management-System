package com.project.lms.utils;

import com.project.lms.entity.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService implements UserDetailsService {
    @Autowired
    AuthUserRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByUserName(username);
    }

    public AuthUser saveAuthUser(AuthUser authUser, String userType){
        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
        String authorities=Authorities.getAuthorities().get(userType);
        authUser.setAuthorities(authorities);
        return repo.save(authUser);
    }
}
