package com.hamid.userservice;

import com.hamid.userservice.domain.Role;
import com.hamid.userservice.domain.User;
import com.hamid.userservice.service.UserService;
import java.util.ArrayList;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null, "Hamid Farmani","hamidfarmani","1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Negin Enshaei","neg","1234", new ArrayList<>()));
			userService.saveUser(new User(null, "manager","manager","1234", new ArrayList<>()));
			userService.saveUser(new User(null, "admin","admin","1234", new ArrayList<>()));
			userService.saveUser(new User(null, "super admin","super-admin","1234", new ArrayList<>()));


			userService.addRoleToUser("hamidfarmani","ROLE_USER");
			userService.addRoleToUser("hamidfarmani","ROLE_MANAGER");
			userService.addRoleToUser("neg","ROLE_USER");
			userService.addRoleToUser("neg","ROLE_SUPER_ADMIN");
			userService.addRoleToUser("manager","ROLE_MANAGER");
			userService.addRoleToUser("manager","ROLE_MANAGER");
			userService.addRoleToUser("admin","ROLE_ADMIN");
			userService.addRoleToUser("super-admin","ROLE_SUPER_ADMIN");

		};
	}

}
