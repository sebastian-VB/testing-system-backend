package com.sistema.examenes.config;

import com.sistema.examenes.services.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    //este metodo es un filro personalizado para manejar la autenticacion y autorizacion
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION); //extrae el token del encabezado authorization

        String username = null;
        String jwtToken = null;

        //si el token esta presente y comienza con bearer
        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")){
            jwtToken = requestTokenHeader.substring(7); //se extrae el token real del encabezado
            //username = this.jwtUtil.extractUsername(jwtToken);

            try{
                username = jwtUtil.extractUsername(jwtToken); //extrae el nombre del usuario

            }catch(ExpiredJwtException expiredJwtException){
                System.out.println("El token a expirado");

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Token invalido, no empieza con bearer string");
        }

        //el nombre de usuario es extraido con exito y el usuario no ha sido autenticado previamente
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails = userDetailsService.loadUserByUsername(username); //se devuelve un usuario del repositorio

            //si el token es valido
            if(jwtUtil.validateToken(jwtToken, userDetails)){
                //se crea un objeto UsernamePasswordAuthenticationToken con userdetails
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //se establece en el SecurityHolder y se completa la autenticacion
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            /*else{
                //si no es valido se imprime un mensaje y se continua con el siguiente filtro o cadenas de filtros
                System.out.println("El token no es valido");
                filterChain.doFilter(request, response);
            }*/
        }
        filterChain.doFilter(request, response);


    }
}
