/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.seguranca;

import br.com.lacetecnologia.rpgdatabase.estrutura.Erro;
import br.com.lacetecnologia.rpgdatabase.fachada.UsuarioFachada;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Leticia Sena
 */
@Provider
public class AutenticacaoFiltro implements ContainerRequestFilter{
    
    @Context
    private ResourceInfo resourceInfo;
    @EJB
    private UsuarioFachada fachada;
    
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
   
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Method method = resourceInfo.getResourceMethod();
        //Access allowed for all
        if( !method.isAnnotationPresent(PermitAll.class)){
            //Access denied for all
            if(method.isAnnotationPresent(DenyAll.class)){
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity(new Erro(403,"Acesso negado a todos os usuários!")).build());
                return;
            }
              
            //Get request headers
            final MultivaluedMap<String, String> headers = requestContext.getHeaders();
              
            //Fetch authorization header
            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);
              
            //If no authorization information present; block access
            if(authorization == null || authorization.isEmpty())
            {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new Erro(401,"Acesso Negado!")).build());
                return;
            }
              
            //Get encoded username and password
            final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
              
            //Decode username and password
            String usernameAndPassword = new String(Base64.getDecoder().decode(encodedUserPassword.getBytes()));
  
            //Split username and password tokens
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();
              
            try {
                //Is user valid?
                if( ! fachada.loginPermitido(username, password))
                {
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(new Erro(401,"Acesso Negado!")).build());
                    return;
                }
            } catch (Exception ex) {
                requestContext.abortWith(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Erro(500,"Erro no servidor!",ex.getLocalizedMessage())).build());
                return;
            }
        }
    }

    
    
}
