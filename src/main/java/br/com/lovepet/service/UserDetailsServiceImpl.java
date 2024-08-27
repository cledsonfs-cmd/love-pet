package br.com.lovepet.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UserDetailsServiceImpl {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
