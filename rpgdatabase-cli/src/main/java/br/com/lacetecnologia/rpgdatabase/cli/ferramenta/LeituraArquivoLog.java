/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lacetecnologia.rpgdatabase.cli.ferramenta;

import br.com.lacetecnologia.rpgdatabase.ferramenta.Formatador;
import br.com.lacetecnologia.rpgdatabase.ferramenta.Padronizador;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Leticia Sena
 */
public class LeituraArquivoLog {
    private String nomeJogo;
    private String nomeLogico;
    private List<String> apelidos;
    private Date inicio;
    private Date fim;
    private Integer linhas;
    
    public LeituraArquivoLog(File arquivo) throws IOException, FileNotFoundException, ParseException{
        apelidos = new ArrayList<String>();
        this.lerArquivo(arquivo);
        if(nomeJogo == null || nomeLogico == null){
            nomeJogo = arquivo.getName().replaceFirst("\\..+", "");
            nomeLogico = Padronizador.nomeLogicoJogo(nomeJogo);
        }
    }

    public String getNomeJogo() {
        return nomeJogo;
    }

    public void setNomeJogo(String nomeJogo) {
        this.nomeJogo = nomeJogo;
    }

    public String getNomeLogico() {
        return nomeLogico;
    }

    public void setNomeLogico(String nomeLogico) {
        this.nomeLogico = nomeLogico;
    }

    public List<String> getApelidos() {
        return apelidos;
    }

    public void setApelidos(List<String> apelidos) {
        this.apelidos = apelidos;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public Integer getLinhas() {
        return linhas;
    }

    public void setLinhas(Integer linhas) {
        this.linhas = linhas;
    }

    private void lerArquivo(File arquivo) throws FileNotFoundException, IOException, ParseException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo)));
        this.linhas = 0;
        String texto = "";
        String ultimaLinha = "";
        while((texto = reader.readLine())!= null){
            if(!texto.isEmpty()){
                if(texto.matches(ParametroRegex.getInstance().getRegexNomeJogo()))
                    buscarNomeJogo(texto);
                if(linhas == 0){
                    buscarInicio(texto);
                }
                populaApelido(texto);
                ultimaLinha = texto;
                linhas++;
            }
        }
        buscarFim(ultimaLinha);
        reader.close();
    }

    private void buscarNomeJogo(String texto) {
        Pattern patNomeJogo = Pattern.compile(ParametroRegex.getInstance().getRegexNomeJogo());
        Matcher buscaNomeJogo = patNomeJogo.matcher(texto);
        if(buscaNomeJogo.find()){
            this.nomeJogo = buscaNomeJogo.group(2);
            this.nomeLogico = Padronizador.nomeLogicoJogo(nomeJogo);
        }
    }

    private void buscarInicio(String texto) throws ParseException {
       String dia, mes, ano, horario, dataCompleta;
       Pattern patInicio = Pattern.compile(ParametroRegex.getInstance().getRegexInicioJogo());
       Matcher buscaInicioJogo = patInicio.matcher(texto);
       if(buscaInicioJogo.find()){
            dia = buscaInicioJogo.group(3);
            mes = String.format("%02d", Padronizador.mesParaInteger(buscaInicioJogo.group(2)));
            ano = buscaInicioJogo.group(5);
            horario = buscaInicioJogo.group(4);
            dataCompleta = dia+"-"+mes+"-"+ano+" "+horario;
            this.inicio = Formatador.formataStringParaDateTime(dataCompleta);
       }
    }

    private void buscarFim(String texto) throws ParseException {
       String dia, mes, ano, horario, dataCompleta;
       Pattern patFim = Pattern.compile(ParametroRegex.getInstance().getRegexFimJogo());
       Matcher buscaFimJogo = patFim.matcher(texto);
       if(buscaFimJogo.find()){
            dia = buscaFimJogo.group(4);
            mes = String.format("%02d", Padronizador.mesParaInteger(buscaFimJogo.group(3)));
            if(buscaFimJogo.group(6).length() == 4)
                ano = buscaFimJogo.group(6);
            else
                ano = String.valueOf(inicio.getYear());
            horario = buscaFimJogo.group(5);
            dataCompleta = dia+"-"+mes+"-"+ano+" "+horario;
            this.fim = Formatador.formataStringParaDateTime(dataCompleta);
       }
    }

    private void populaApelido(String texto) {
        Pattern patApelido = Pattern.compile(ParametroRegex.getInstance().getRegexApelido());
        Matcher buscaApelido = patApelido.matcher(texto);
        String apelido;
        if(buscaApelido.find()){
            apelido = buscaApelido.group(3);
            boolean existe = false;
            for(String nick:apelidos){
                if(nick.equalsIgnoreCase(apelido)){
                    existe = true;
                    break;
                }
            }
            if(!existe) apelidos.add(apelido);
        }
    }
    
    
}
