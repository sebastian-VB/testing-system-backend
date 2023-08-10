package com.sistema.examenes.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

//esta clase se encarga de manejar la generacion y validacion de tokens JWT
@Service
public class JwtUtil {

    private String SECRET_KEY = "examportal";

    //este metodo toma un JWT y devuelve el nombre del usuario extraido del token
    //utiliza el metodo extractClaims para extraer el contenido del subject del token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //este metodo toma un JWT como argumento y devuelve la fecha de expiracion del token
    //extrae el campo expiration del token utilizado
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //este metodo toma un JWT y una funcion claimsResolver que es responsable de extraer un campo especifico de las claims del token
    //utiliza una funcion para extraer el contenido de la clain especifica
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //este metodo utilza la biblioteca JJWT para analizar el token y extraer las claims del token
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    //este metodo verifica si el token a expirado, toma la fecha deexpiracion del token con la fecha actual
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //este metodo toma un objeto userdetails y genera un nuevo token
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    //este metodo crea un nuevo JWT utilizando la libreria JJWT
    //recibe un mapa de claims y un subject (nombre de usuario) como argumento
    //configura las claims y los campos del token (incluyendo la fecha de emision y expiracion)
    //firma el token con una clave secreta
    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    //este metodo toma un JWT y un objeto userDetails (representa los detalles del usuario)
    //verifica si el nombre de usuario extraido del token coincide con el nombre de usuario del objeto userDetails proporcionado
    //y si el token a expirado utilizando isTokenExpired
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
