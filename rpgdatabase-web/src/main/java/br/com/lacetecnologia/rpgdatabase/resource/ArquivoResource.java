/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.resource;

import br.com.lacetecnologia.rpgdatabase.estrutura.Erro;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Leticia Sena
 */

@Path("/arquivos")
@Stateless
public class ArquivoResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response helloWorld(){
        return Response.status(Response.Status.OK).entity(new Erro(200,"Hello World!")).build();
    }
    
}
