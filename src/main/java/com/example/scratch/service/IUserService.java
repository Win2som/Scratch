package com.example.scratch.service;

import com.example.scratch.model.User;

import java.util.Optional;

public interface IUserService {
  User saveUser(User user);

  Optional<User> findByUsername(String username);

  void makeAdmin(String username);
}
