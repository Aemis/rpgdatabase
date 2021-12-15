/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.resource;

import br.com.lacetecnologia.rpgdatabase.estrutura.Erro;
import br.com.lacetecnologia.rpgdatabase.estrutura.RegistroJogo;
import br.com.lacetecnologia.rpgdatabase.fachada.JogoFachada;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
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
@Path("/jogos")
@Stateless
public class JogoResource {
    @EJB
    private JogoFachada fachada;
    
   
    
}
