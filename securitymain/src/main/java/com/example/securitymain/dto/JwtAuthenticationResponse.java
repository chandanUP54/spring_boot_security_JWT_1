package com.example.securitymain.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {

	private String token;
	private String RefreshToken;

}
