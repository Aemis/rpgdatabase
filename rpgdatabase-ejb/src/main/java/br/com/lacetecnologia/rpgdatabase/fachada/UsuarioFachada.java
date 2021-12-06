package br.com.lacetecnologia.rpgdatabase.fachada;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.lacetecnologia.rpgdatabase.dao.UsuarioDAO;
import br.com.lacetecnologia.rpgdatabase.enumerados.ParametroNome;
import br.com.lacetecnologia.rpgdatabase.estrutura.Sessao;
import br.com.lacetecnologia.rpgdatabase.modelo.Usuario;
import br.com.lacetecnologia.rpgdatabase.modelo.Parametro;
import br.com.lacetecnologia.rpgdatabase.ferramenta.Criptografia;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Leticia Sena
 */
@Stateless
public class UsuarioFachada {
    @EJB 
    private UsuarioDAO dao;
    @EJB
    private ParametroFachada parametroFachada;
    
    public Boolean loginPermitido(String email,String senha) throws NoSuchAlgorithmException,Exception{
        
        if(email != null && senha != null){
            if(!email.isEmpty() && !senha.isEmpty()){
                Usuario usuario = dao.porEmail(email);
                if(usuario != null){
                    String senhaCript = Criptografia.criptografar(senha,getChaveCritpografia());
                    return (usuario.getSenha().equals(senhaCript) && usuario.getAtivo());
                }
            }
        }
        throw new Exception("Usuário e/ou senha incorretos!");
        
    }
    
    public Boolean loginWEB(String email,String senha) throws NoSuchAlgorithmException,Exception{
        Boolean permite = loginPermitido(email, senha);
        if(permite){
            Sessao.getInstance().setUsuario(dao.porEmail(email));
        }
        return permite;
    }
    
    /**
     * Recupera a chave de Criptografia para uso na criptografia das senhas dos usuários.
     * @return
     * @throws NoSuchAlgorithmException 
     */
    private String getChaveCritpografia() throws  NoSuchAlgorithmException{
        String chave;
        Parametro parm = parametroFachada.porNome(ParametroNome.CHAVE_CRIPTOGRAFIA);
        if(parm == null){
            chave = Criptografia.getSalt();
            parametroFachada.inserir(ParametroNome.CHAVE_CRIPTOGRAFIA, chave);
        }else
            chave = parm.getValor().trim();
        return chave;
    }
    
    public void adicionar(String nome,String email,String senha) throws NoSuchAlgorithmException{
        Usuario novo = new Usuario();
        novo.setEmail(email);
        novo.setNome(nome);
        novo.setEntrada(new Date());
        novo.setSenha(Criptografia.criptografar(senha, parametroFachada.porNome(ParametroNome.CHAVE_CRIPTOGRAFIA).getValor()));
        novo.setAtivo(true);
        dao.inserir(novo);
    }
    
    public void alterar(Integer codigo, String nome, String email, Boolean ativo){
        Usuario atualizar = dao.porCodigo(codigo);
        atualizar.setNome(nome);
        atualizar.setEmail(email);
        atualizar.setAtivo(ativo);
        dao.alterar(atualizar);
    }

    public void inicializa() throws NoSuchAlgorithmException {
        //Inicializa o banco de dados
        String chave = getChaveCritpografia();
        this.adicionar("Leticia Sena","lecmsena@gmail.com", "let123");
    }
    
    public List<Usuario> buscarTodos(){
        return dao.buscarTodos();
    }
    
}
