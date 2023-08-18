

package br.com.lacetecnologia.rpgdatabaseclient;

import br.com.lacetecnologia.rpgdatabaseclient.estrutura.ArquivoJogo;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;

import java.time.LocalDate;
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
        File pastaBase = new File(PASTA_DOS_LOGS);
        if(pastaBase.isDirectory()){
            try {
                listarArquivos(pastaBase);
            } catch (IOException ex) {
               System.err.println("OCORREU UM ERRO NA EXECUÇÃO: "+ex.getLocalizedMessage());
            }
        }else{
            System.err.println("A PASTA "+PASTA_DOS_LOGS+" NÃO É UMA PASTA VÁLIDA! VERIFIQUE!");
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
                    novo.setNomeJogo(arquivo.getName());
                    novo.setCaminhoArquivo(arquivo.getAbsolutePath());
                    novo.setDataUltimaModificacao(atributo.lastModifiedTime());
                    novo.setDataCriacao(atributo.creationTime());
                    
                    arquivosDeJogos.add(novo);
                    System.out.println("- Encontrado "+novo);
                }
            }
        }
    }

}
