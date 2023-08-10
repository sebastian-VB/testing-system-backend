package com.sistema.examenes.entities;

import org.springframework.security.core.GrantedAuthority;

//GrantedAuthority: esta interfaz es utilizada para representar una autoridad o un permiso que un usuario posee dentro de la aplicacion
public class Authority implements GrantedAuthority {

    private String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    //este metodo es utilizado por el sistema de seguridad para obtener el nombre de la autoridad asociada con un usuario en particular
    @Override
    public String getAuthority() {
        return this.authority;
    }
}
