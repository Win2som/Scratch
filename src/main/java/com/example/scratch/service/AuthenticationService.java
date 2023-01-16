package com.example.scratch.service;


import com.example.scratch.model.User;
import com.example.scratch.security.UserPrincipal;
import com.example.scratch.security.jwt.IJwtProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService{

  @Autowired
  private AuthenticationManager mAuthenticationManager;
  @Autowired
  private IJwtProvider mJwtProvider;


  @Override
  public User signIn(User sigInRequest){

    Authentication authentication = mAuthenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(sigInRequest.getUsername(), sigInRequest.getPassword()));

    UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

    String jwt = mJwtProvider.generateToken(userPrincipal);

    User sigInUser = userPrincipal.getUser();
    sigInUser.setToken(jwt);

    return sigInUser;
  }

}
