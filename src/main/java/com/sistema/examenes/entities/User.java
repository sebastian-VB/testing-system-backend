package com.sistema.examenes.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements UserDetails{
    //UserDetails: es utilizada para representar información de usuarios en aplicaciones que requieren autenticación y autorización.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private boolean enabled= true;
    private String profile;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "user"
    )
    @JsonIgnore
    private Set<UserRole> userRoles = new HashSet<>();

    public User(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    //este metodo indica si la cuenta del usuario ha expirado - false: la cuenta exta considerada como expirada
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //este metodo indica si la cuenta del usuario esta bloqueada - true: la cuenta no esta bloqueada
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //este metodo indica si las credenciales del usuario han expirado (como la ocntraseña) - true: las credenciales aun son validas
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //este metodo devuelve una coleccion de las autoridades(roles o permisos ) asociados al usuario
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> autoridades = new HashSet<>();
        this.userRoles.forEach(userRole -> {
            autoridades.add(new Authority(userRole.getRole().getName()));
        });

        return autoridades;
    }

}
