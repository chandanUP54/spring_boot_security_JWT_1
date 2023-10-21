package com.example.securitymain.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.securitymain.entities.Role;
import com.example.securitymain.services.impl.UserServiceImpl;


@Configuration
@EnableWebSecurity

public class SecurityConfiguration {

	
	
	public SecurityConfiguration() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter) {
		super();
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Bean
	  public UserDetailsService userDetailsService() { 
	     return new UserServiceImpl(); 
	  } 
	
	 @Bean
	  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		  http.csrf(csrf -> csrf.disable())
	 
		  .authorizeHttpRequests(request->request.requestMatchers("/api/v1/auth/**")
				  .permitAll()
				  .requestMatchers("/api/v1/admin").hasAnyAuthority(Role.ADMIN.name())
				  .requestMatchers("/api/v1/user").hasAnyAuthority(Role.USER.name())
				  .anyRequest().authenticated())
	
	     .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

		 .authenticationProvider(authenticationProvider())
		 .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		  
		    return http.build();
	  }
	 
	 
	    @Bean
	    public AuthenticationProvider authenticationProvider() { 
	        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); 
	        authenticationProvider.setUserDetailsService(userDetailsService()); 
	        authenticationProvider.setPasswordEncoder(passwordEncoder()); 
	        return authenticationProvider; 
	    } 
	    
	    @Bean
	    public PasswordEncoder passwordEncoder() { 
	        return new BCryptPasswordEncoder(); 
	    } 
	  
	    //provide bean for authentication manager
	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception { 
	        return config.getAuthenticationManager(); 
	    } 
	    
}
