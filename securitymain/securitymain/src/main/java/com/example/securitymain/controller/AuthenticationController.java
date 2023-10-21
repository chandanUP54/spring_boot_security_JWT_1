package com.example.securitymain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.securitymain.dto.JwtAuthenticationResponse;
import com.example.securitymain.dto.RefreshTokenRequest;
import com.example.securitymain.dto.SignUpRequest;
import com.example.securitymain.dto.SigninRequest;
import com.example.securitymain.entities.Users;
import com.example.securitymain.services.AuthenticationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

	@Autowired
	private  AuthenticationService authenticationService;
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello xy";
	}
	
	@PostMapping("/signup")
	public ResponseEntity<Users> signup(@RequestBody SignUpRequest signUpRequest){
		return ResponseEntity.ok(authenticationService.signup(signUpRequest));
		
	}
	
	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest signinRequest){
		return ResponseEntity.ok(authenticationService.signin(signinRequest));
		
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
		return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
		
	}
	
}
