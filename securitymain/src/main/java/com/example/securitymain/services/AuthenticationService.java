package com.example.securitymain.services;

import com.example.securitymain.dto.JwtAuthenticationResponse;
import com.example.securitymain.dto.RefreshTokenRequest;
import com.example.securitymain.dto.SignUpRequest;
import com.example.securitymain.dto.SigninRequest;
import com.example.securitymain.entities.Users;

public interface AuthenticationService {

   Users signup(SignUpRequest signUpRequest) ;
  public JwtAuthenticationResponse signin(SigninRequest signinRequest) ;
  public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
