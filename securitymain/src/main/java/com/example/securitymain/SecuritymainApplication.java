package com.example.securitymain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.securitymain.entities.Role;
import com.example.securitymain.entities.Users;
import com.example.securitymain.repository.UserRepository;

@SpringBootApplication
public class SecuritymainApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SecuritymainApplication.class, args);
	}

	@Autowired
	private  PasswordEncoder passwordEncoder ;
	
	@Autowired
	private UserRepository userRepository;
	
	public void run(String... args) {
		Users adminAccount=userRepository.findByRole(Role.ADMIN);
		if(adminAccount==null) {
			Users users=new Users();
			users.setEmail("admin2@gmail.com");
			users.setFirstName("admin2");
			users.setSecondName("admin2");
			users.setRole(Role.ADMIN);
//			users.setPassword(new BCryptPasswordEncoder().encode("admin1"));
			users.setPassword(passwordEncoder.encode("admin2"));
			userRepository.save(users);
		}
	}
	
}
