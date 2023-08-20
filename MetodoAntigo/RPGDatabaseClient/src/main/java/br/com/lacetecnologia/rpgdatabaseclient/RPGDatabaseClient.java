

package br.com.lacetecnologia.rpgdatabaseclient;

import br.com.lacetecnologia.rpgdatabaseclient.estrutura.ArquivoJogo;
import br.com.lacetecnologia.rpgdatabaseclient.estrutura.Jogo;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leticiasena
 */
public class RPGDatabaseClient {
    private static final String PASTA_DOS_LOGS = "/home/leticiasena/RPG/TesteLogs";
    private static final List<ArquivoJogo> arquivosDeJogos = new ArrayList<ArquivoJogo>();

    public static void main(String[] args) {
        try {
            LeitorLOG ll = new LeitorLOG("/home/leticiasena/RPG/TesteLogs/20151024#FutureIsNow.log");
            
//        File pastaBase = new File(PASTA_DOS_LOGS);
//        if(pastaBase.isDirectory()){
//            try {
//                listarArquivos(pastaBase);
//                leituraJogos();
//            } catch (IOException ex) {
//               System.err.println("OCORREU UM ERRO NA EXECUÇÃO: "+ex.getLocalizedMessage());
//            }
//        }else{
//            System.err.println("A PASTA "+PASTA_DOS_LOGS+" NÃO É UMA PASTA VÁLIDA! VERIFIQUE!");
//        }
        } catch (IOException ex) {
            Logger.getLogger(RPGDatabaseClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void listarArquivos(File pastaBase) throws IOException {
        System.out.println("Encontrei a pasta: "+pastaBase.getPath());
        for(File arquivo:pastaBase.listFiles()){
            if(arquivo.isDirectory()){
                
                listarArquivos(arquivo);
            }else{
                if(arquivo.getName().endsWith(".log")){
                    BasicFileAttributes atributo = Files.readAttributes(arquivo.toPath(), BasicFileAttributes.class);
        
                    ArquivoJogo novo = new ArquivoJogo();
                    novo.setNomeJogo(arquivo.getName().replace(".log", "").replace("#",""));
                    novo.setCaminhoArquivo(arquivo.getAbsolutePath());
                    novo.setDataUltimaModificacao(atributo.lastModifiedTime());
                    novo.setDataCriacao(atributo.creationTime());
                    novo.setTamanho(atributo.size());
                    
                    arquivosDeJogos.add(novo);
                }
            }
        }
    }

    private static void leituraJogos() {
        for(ArquivoJogo arquivo: arquivosDeJogos){
            Jogo jogo = new Jogo(arquivo.getNomeJogo());
//            LeitorLOG novo = new LeitorLOG();
            arquivo.setJogo(jogo);
        }
    }

}
