/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.fachada;

import br.com.lacetecnologia.rpgdatabase.dao.ArquivoDAO;
import br.com.lacetecnologia.rpgdatabase.modelo.Arquivo;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Leticia Sena
 */
@Stateless
public class ArquivoFachada {
    @EJB
    private ArquivoDAO dao;
    
    public List<Arquivo> buscarTodos(Integer usuarioCodigo){
        return dao.buscarTodos(usuarioCodigo);
    }
}
