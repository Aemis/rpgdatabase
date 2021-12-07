/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.cli;

import br.com.lacetecnologia.rpgdatabase.cli.ferramenta.Mensageria;
import br.com.lacetecnologia.rpgdatabase.estrutura.Configuracao;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Leticia
 */
public final class ArquivoConfiguracao {
    private static final ArquivoConfiguracao INSTANCIA = new ArquivoConfiguracao();
    private Configuracao configuracao;
    private File arquivoCfg;    
   
    
    public static ArquivoConfiguracao getInstance(){
        return INSTANCIA;
    }
    
    private ArquivoConfiguracao(){//Carrega o arquivo de Configuração automaticamente
        String caminhoArquivoConfiguracao = System.getProperty("user.dir");
        File pasta = new File(caminhoArquivoConfiguracao);
        if(pasta.exists()){//Verifique se a pasta existe
            String nomeArquivo = pasta.getAbsolutePath() + File.separator+"configuracao.cfg";
            arquivoCfg = new File(nomeArquivo);
            abrirConfiguracao();
        }else{
            Mensageria.mostrarMensagemErroNaTela(this.getClass().getName(), "A pasta especificada não existe!");
        }
    }
    
    private void abrirConfiguracao(){//Recupera a configuração
        if(arquivoCfg.exists()){//Abra
            try{
                JAXBContext contexto = JAXBContext.newInstance(Configuracao.class);
                Unmarshaller jaxbMarsh = contexto.createUnmarshaller();
                this.configuracao = (Configuracao) jaxbMarsh.unmarshal(arquivoCfg);
            }catch(Exception e){
                Mensageria.mostrarMensagemErroNaTela(this.getClass().getName(), "Não foi possível abrir o arquivo!");
            }
        }else{//Crie
           configuracao = new Configuracao();
           salvar();
        }
    }
    
    
    
    private void salvar(){//Salva o arquivo de configuração
        try{
            JAXBContext contexto = JAXBContext.newInstance(Configuracao.class);
            Marshaller jaxbMarsh = contexto.createMarshaller();
            jaxbMarsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarsh.marshal(configuracao,arquivoCfg);
        }catch(Exception e){
            Mensageria.mostrarMensagemErroNaTela(this.getClass().getName(),"Não foi possível salvar o novo arquivo!");
            e.printStackTrace();
        }   
    }

    public Configuracao getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(Configuracao configuracao) {
        this.configuracao = configuracao;
    }
    
    public String getNomeArquivoCfg(){
        return this.arquivoCfg.getAbsolutePath();
    }
    
}
