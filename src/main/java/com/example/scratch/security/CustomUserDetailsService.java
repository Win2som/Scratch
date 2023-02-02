package com.example.scratch.security;

import com.example.scratch.model.User;
import com.example.scratch.service.IUserService;
import com.example.scratch.util.SecurityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private IUserService userService;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userService.findByUsername(username)
      .orElseThrow(() -> new UsernameNotFoundException(username));

    Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(user.getRole().name()));

    return UserPrincipal.builder()
      .id(user.getId())
      .username(username)
      .password(user.getPassword())
      .user(user)
      .authorities(authorities)
                        .build();
  }
}
