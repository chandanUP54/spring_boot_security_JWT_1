package com.example.securitymain.dto;




public class SignUpRequest {

	private String firstName;
	private String secondName;
	private String email;
	private String password;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public SignUpRequest(String firstName, String secondName, String email, String password) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
		this.email = email;
		this.password = password;
	}
	public SignUpRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SignUpRequest [firstName=" + firstName + ", secondName=" + secondName + ", email=" + email
				+ ", password=" + password + "]";
	}
		
	
	
}
