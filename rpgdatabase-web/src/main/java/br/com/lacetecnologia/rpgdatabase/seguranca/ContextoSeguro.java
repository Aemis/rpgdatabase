/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.seguranca;

import java.security.Principal;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author Leticia Sena
 */
public class ContextoSeguro implements SecurityContext{
    private String email;
    
    public ContextoSeguro(String email){
        this.email = email;
    }

    @Override
    public Principal getUserPrincipal() {
        return new Principal(){
            
            @Override
            public String getName(){
                return email.toString();
            }
        };
    }

    @Override
    public boolean isUserInRole(String role) {
        return true;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getAuthenticationScheme() {
        return null;
    }
    
}
