package com.example.scratch.service;

import com.example.scratch.model.User;

public interface IAuthenticationService {
  User signIn(User sigInRequest);
}
