package com.example.scratch.controller;


import com.example.scratch.model.User;
import com.example.scratch.service.IAuthenticationService;
import com.example.scratch.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

  @Autowired
  private IAuthenticationService mAuthenticationService;
  @Autowired
  private IUserService mUserService;


  @PostMapping("/sign-up")
  public ResponseEntity<User> signUp(@RequestBody User user){

    if(mUserService.findByUsername(user.getUsername()).isPresent()){
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>(mUserService.saveUser(user), HttpStatus.CREATED);
  }


  @PostMapping("/sign-in")
  public ResponseEntity<User> signIn(@RequestBody User user){

    return new ResponseEntity<>(mAuthenticationService.signIn(user), HttpStatus.CREATED);
  }

}
