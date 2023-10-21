package com.example.securitymain.services;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
     // eske teen kam user nikalana , token generate karna , token valid karna 
	 String extractUsername(String token);
	 String generateToken(UserDetails userDetails);
	  boolean isTokenValid(String token,UserDetails userDetails);
	  public String generateRefreshToken(Map<String, Object> extraClaims,UserDetails userDetails);
}
