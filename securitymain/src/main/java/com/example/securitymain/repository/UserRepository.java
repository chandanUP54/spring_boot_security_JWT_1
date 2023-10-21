package com.example.securitymain.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.securitymain.entities.Role;
import com.example.securitymain.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{

	Optional<Users> findByEmail(String email);
	
	Users findByRole(Role role);
}
