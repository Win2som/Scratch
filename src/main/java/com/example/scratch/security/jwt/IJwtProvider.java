package com.example.scratch.security.jwt;

import com.example.scratch.security.UserPrincipal;

import org.springframework.security.core.Authentication;

import jakarta.servlet.http.HttpServletRequest;

public interface IJwtProvider {
  String generateToken(UserPrincipal auth);

  Authentication getAuthentication(HttpServletRequest request);

  boolean validateToken(HttpServletRequest request);
}
