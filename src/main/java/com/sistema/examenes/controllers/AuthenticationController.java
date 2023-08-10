package com.sistema.examenes.controllers;

import com.sistema.examenes.config.JwtUtil;
import com.sistema.examenes.entities.JwtRequest;
import com.sistema.examenes.entities.JwtResponse;
import com.sistema.examenes.entities.User;
import com.sistema.examenes.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/generate-token")
    public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        //se intenta autenticar el usuario y la contraseña
        try {
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        }
        catch(UsernameNotFoundException exception){
            exception.getMessage();
            throw new Exception("Usuario no encontrado");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername()); // se carga el objeto correspondiente al username
        String token = jwtUtil.generateToken(userDetails); //se genera el token

        return ResponseEntity.ok(new JwtResponse(token));

    }

    private void authenticate(String username, String password) throws Exception {
        try{
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

        }
        catch(DisabledException disabledException){
            throw new Exception("USUARIO DESHABILITADO " + disabledException.getMessage());
        }
        catch(BadCredentialsException badCredentialsException){
            throw new Exception("Credenciales inválidas " + badCredentialsException.getMessage());
        }
    }

    //Principal: es una interfaz de spring security que representa el usuario autenticado actual
    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){
        return (User) userDetailsService.loadUserByUsername(principal.getName()); //se obtienen los detalles del usuario actual autenticado
    }

}
