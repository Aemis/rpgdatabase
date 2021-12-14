/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.resource;

import br.com.lacetecnologia.rpgdatabase.estrutura.Erro;
import br.com.lacetecnologia.rpgdatabase.estrutura.RegistroArquivo;
import br.com.lacetecnologia.rpgdatabase.estrutura.RegistrosArquivos;
import br.com.lacetecnologia.rpgdatabase.fachada.ArquivoFachada;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author Leticia Sena
 */

@Path("/arquivos")
@Stateless
public class ArquivoResource {
    
    @EJB
    private ArquivoFachada fachada;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArquivos(@Context SecurityContext securityContext){
        try{
            RegistrosArquivos resultado = fachada.buscarArquivosPorUsuario(securityContext.getUserPrincipal().getName());
            return Response.status(Response.Status.OK).entity(resultado).build();
        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Erro(500,"Ocorreu um erro ao executar:"+e.getMessage())).build();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)  
    @Produces(MediaType.APPLICATION_JSON)
    public Response setArquivo(RegistroArquivo registroArquivo,@Context SecurityContext securityContext){
        try{
            if(registroArquivo != null){
                return Response.status(Response.Status.CREATED).entity(fachada.inserir(registroArquivo,securityContext.getUserPrincipal().getName())).build();
            }else{
                return Response.status(Response.Status.BAD_REQUEST).entity(new Erro(400,"Por favor, envie os dados para o arquivo a ser inserido!")).build();
            }
        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Erro(500,"Ocorreu um erro ao executar:"+e.getMessage())).build();
        }
    }
    
}
