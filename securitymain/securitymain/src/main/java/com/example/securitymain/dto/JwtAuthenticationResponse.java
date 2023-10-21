package com.example.securitymain.dto;

public class JwtAuthenticationResponse {

	private String token;
	private String RefreshToken;
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRefreshToken() {
		return RefreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		RefreshToken = refreshToken;
	}
	
	

}
