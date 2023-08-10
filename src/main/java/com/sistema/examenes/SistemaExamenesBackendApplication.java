package com.sistema.examenes;

import com.sistema.examenes.entities.Role;
import com.sistema.examenes.entities.User;
import com.sistema.examenes.entities.UserRole;
import com.sistema.examenes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SistemaExamenesBackendApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SistemaExamenesBackendApplication.class, args);
	}

	//cuando s ejecute el programa lo que este en este metodo se ejecutará
	@Override
	public void run(String... args) throws Exception {

		/*User user = new User();

		user.setName("Sebastián");
		user.setLastname("Velásquez");
		user.setUsername("sebastian");
		user.setPassword("123456");
		user.setEmail("seb@gmail.com");
		user.setPhone("967523456");
		user.setProfile("photo.png");

		Role role = new Role();
		role.setId(1L);
		role.setName("ADMIN");

		Set<UserRole> userRoles = new HashSet<>();
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		userRoles.add(userRole);

		User userSave = userService.saveUser(user, userRoles);
		System.out.println(userSave.getUsername());*/

	}
}
