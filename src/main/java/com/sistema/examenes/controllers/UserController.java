package com.sistema.examenes.controllers;

import com.sistema.examenes.entities.Role;
import com.sistema.examenes.entities.User;
import com.sistema.examenes.entities.UserRole;
import com.sistema.examenes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public User getOnlyUser(@PathVariable("username") String username){
        return userService.getOnlyUser(username);
    }

    @PostMapping("/")
    public User saveUser(@RequestBody User user) throws Exception{
        user.setProfile("default.png");
        Set<UserRole> roles = new HashSet<>();

        Role role = new Role();
        role.setId(2L);
        role.setName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);
        roles.add(userRole);

        return userService.saveUser(user, roles);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }

}
