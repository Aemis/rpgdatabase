/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.resource;

import br.com.lacetecnologia.rpgdatabase.enumerados.ParametroNome;
import br.com.lacetecnologia.rpgdatabase.estrutura.Erro;
import br.com.lacetecnologia.rpgdatabase.fachada.ParametroFachada;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Leticia Sena
 */

@Path("/parametro")
@Stateless
public class ValidadorResource {
    @EJB 
    private ParametroFachada fachada;
    
    @GET
    @Path("/{nome}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParam(@PathParam("nome")ParametroNome nome){
        try{
            return Response.status(Response.Status.OK).entity(fachada.porNome(nome)).build();
        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new Erro(500, "Erro interno no servidor!")).build();
        }
    }
    
}
