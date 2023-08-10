package com.sistema.examenes.services.impl;

import com.sistema.examenes.entities.User;
import com.sistema.examenes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//esta interfaz es utilizada para cargar los detalles de un usuario a partir de su nombre de usuario
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //este metodo es llamado cuando un usuario intenta autenticarse, dentro de este metodo se busca un usuario en el repositorio
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        System.out.println("Username: " + user.getName() + " " + user.getLastname());
        if(user == null){
            throw new UsernameNotFoundException("Usuario no encontradox1");
        }

        return user;

    }
}
