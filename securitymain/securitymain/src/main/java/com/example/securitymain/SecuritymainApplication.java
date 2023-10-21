package com.example.securitymain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.securitymain.entities.Role;
import com.example.securitymain.entities.Users;
import com.example.securitymain.repository.UserRepository;

@SpringBootApplication
public class SecuritymainApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SecuritymainApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;
	
	public void run(String... args) {
		Users adminAccount=userRepository.findByRole(Role.ADMIN);
		if(adminAccount==null) {
			Users users=new Users();
			
			users.setEmail("admin1@gmail.com");
			users.setFirstName("admin1");
			users.setSecondName("admin1");
			users.setRole(Role.ADMIN);
			users.setPassword(new BCryptPasswordEncoder().encode("admin1"));
			
			userRepository.save(users);
		}
	}
	
}
