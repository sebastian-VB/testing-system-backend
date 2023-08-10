package com.sistema.examenes.services.impl;

import com.sistema.examenes.entities.User;
import com.sistema.examenes.entities.UserRole;
import com.sistema.examenes.exceptions.UserNotFoundException;
import com.sistema.examenes.repositories.RoleRepository;
import com.sistema.examenes.repositories.UserRepository;
import com.sistema.examenes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public User saveUser(User user, Set<UserRole> userRoles) throws Exception {
        User userLocal = userRepository.findByUsername(user.getUsername());
        if(userLocal != null){
            System.out.println("El usuario ya existe");
            throw new UserNotFoundException("El usuario ya esta presente");
        }
        else{
            for(UserRole userRole:userRoles){
                roleRepository.save(userRole.getRole());
            }
            user.getUserRoles().addAll(userRoles);

            String encodePassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodePassword);

            userLocal = userRepository.save(user);
        }
        return userLocal;

    }

    @Override
    public User getOnlyUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
