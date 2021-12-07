/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.cli.ferramenta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Leticia Sena
 */
public class ContadorDeLinhas {
 
    
    public static Integer contarLinhasArquivo(File arquivo) throws FileNotFoundException, IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo)));
        Integer contador = 0;
        String texto = "";
        while((texto = reader.readLine())!= null){
            if(!texto.isEmpty())
                contador++;
        }
        reader.close();
        return contador;
    }
}
