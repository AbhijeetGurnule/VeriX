package com.project.auth_app;

import com.project.auth_app.config.AppConstants;
import com.project.auth_app.entities.Role;
import com.project.auth_app.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class AuthBackend implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(AuthBackend.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		// we will create some default user role
		// ADMIN
		// GUEST

		roleRepository.findByName("ROLE"+AppConstants.ADMIN_ROLE).ifPresentOrElse(role ->{
			System.out.println("Admin Role Already Exists: "+role.getName());
		}, ()->{
			Role role = new Role();
			role.setName("ROLE_"+AppConstants.ADMIN_ROLE);
			role.setId(UUID.randomUUID());
			roleRepository.save(role);
		});

		roleRepository.findByName("ROLE"+AppConstants.GUEST_ROLE).ifPresentOrElse(role ->{
			System.out.println("Guest Role Already Exists: "+role.getName());
		}, ()->{
			Role role = new Role();
			role.setName("ROLE_"+AppConstants.GUEST_ROLE);
			role.setId(UUID.randomUUID());
			roleRepository.save(role);
		});
	}
}
