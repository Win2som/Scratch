package com.example.scratch.service;

import com.example.scratch.model.Role;
import com.example.scratch.model.User;
import com.example.scratch.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService implements IUserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passWordEncoder;


  @Override
  public User saveUser(User user){
    user.setPassword(passWordEncoder.encode(user.getPassword()));
    user.setRole(Role.USER);
    user.setCreateTime(LocalDateTime.now());

    return userRepository.save(user);
  }

  @Override
  public Optional<User> findByUsername(String username){
    return userRepository.findByUsername(username);
  }

  @Override
  public void makeAdmin(String username){
    userRepository.updateUserRole(username, String.valueOf(Role.ADMIN));
  }
}
