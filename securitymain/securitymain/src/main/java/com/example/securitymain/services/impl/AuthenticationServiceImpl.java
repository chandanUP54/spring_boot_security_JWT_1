package com.example.securitymain.services.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.securitymain.dto.JwtAuthenticationResponse;
import com.example.securitymain.dto.RefreshTokenRequest;
import com.example.securitymain.dto.SignUpRequest;
import com.example.securitymain.dto.SigninRequest;
import com.example.securitymain.entities.Role;
import com.example.securitymain.entities.Users;
import com.example.securitymain.repository.UserRepository;
import com.example.securitymain.services.AuthenticationService;
import com.example.securitymain.services.JwtService;

@Service

public class AuthenticationServiceImpl implements AuthenticationService {
	

	public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	

	public AuthenticationServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Autowired
	private  UserRepository userRepository ;
	
	private  PasswordEncoder passwordEncoder ;
	
	private AuthenticationManager authenticationManager;
	
	private JwtService jwtService;
	
	//for signup
	public Users signup(SignUpRequest signUpRequest) {
		Users users=new Users();
		
		users.setEmail(signUpRequest.getEmail());
		users.setFirstName(signUpRequest.getFirstName());
		users.setSecondName(signUpRequest.getSecondName());
		users.setRole(Role.USER);
		users.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		
	    return	userRepository.save(users);
		
	}
	
	//for signin
	public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
		authenticationManager.authenticate(new 
				UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));
		var users=userRepository.findByEmail(signinRequest.getUsername())
				.orElseThrow(()-> new IllegalArgumentException("Invalid email or password"));
		var jwt=jwtService.generateToken(users);
		var refreshToken=jwtService.generateRefreshToken(new HashMap<>(),users);
		
		JwtAuthenticationResponse jwtAuthenticationResponse=new JwtAuthenticationResponse();
		jwtAuthenticationResponse.setToken(jwt);
		jwtAuthenticationResponse.setRefreshToken(refreshToken);
		return jwtAuthenticationResponse;
	}
	
	
	public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		String userEmail=jwtService.extractUsername(refreshTokenRequest.getToken());
		Users users=userRepository.findByEmail(userEmail).orElseThrow();
		if(jwtService.isTokenValid(refreshTokenRequest.getToken(), users)) {
			var jwt=jwtService.generateToken(users);
			
			JwtAuthenticationResponse jwtAuthenticationResponse=new JwtAuthenticationResponse();
			jwtAuthenticationResponse.setToken(jwt);
			jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
			return jwtAuthenticationResponse;
		}
		return null;
	}
	
}
