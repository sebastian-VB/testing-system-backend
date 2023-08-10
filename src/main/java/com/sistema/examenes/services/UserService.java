package com.sistema.examenes.services;

import com.sistema.examenes.entities.User;
import com.sistema.examenes.entities.UserRole;

import java.util.Set;

public interface UserService {

    public User saveUser(User user, Set<UserRole> userRoles) throws Exception;

    public User getOnlyUser(String username);

    public void deleteUser(Long userId);

}
