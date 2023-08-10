package com.sistema.examenes.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*
* esta clase actúa como un punto de entrada para las solicitudes no autorizadas,
* y se utiliza para enviar una respuesta de error cuando un usuario intenta acceder
* a un recurso protegido sin la autenticación adecuada.
*/
//AuthenticationEntryPoint: esta interfaz es utilzada para manejar situaciones en las que un usuario no esta autorizado para acceder a un recurso protegido
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    //este metodo es invocado cuando se prouce una excepcion de autenticacion
    //cuando se produce una excepcion  se envia una respuesta y un mensaje
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuario no autorizado");
    }
}
