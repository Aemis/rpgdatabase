/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.seguranca;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.logging.LoggingFeature;

/**
 *
 * @author Leticia Sena
 */

public class CustomApp extends ResourceConfig{

    public CustomApp(){
        packages("br.com.lacetecnologia.rpgdatabase.resource");
        register(LoggingFeature.class);
        register(AutenticacaoFiltro.class);
        
    }
    
}
