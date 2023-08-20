package br.com.lacetecnologia.rpgdatabaseclient;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Recupera valores em um determinado Log 
 * @author leticiasena
 */
public class LeitorLOG {
    private String caminho;
    private final String REGEX_INICIO_SESSAO = "(?:\\*\\*\\*\\* (?:REGISTRO COMEÇANDO EM|REGISTO INICIADO EM|BEGIN LOGGING AT)|Session Start:) \\w{3} (\\w{3}) (\\d{2}| \\d{1}) (\\d{2}):(\\d{2}):(\\d{2}) ((\\d{4}))";
    private final String REGEX_FIM_SESSAO = "(?:\\*\\*\\*\\* (?:REGISTRO FINALIZANDO EM|REGISTO TERMINADO EM|ENDING LOGGING AT)|Session Close:) \\w{3} (\\w{3}) (\\d{2}| \\d{1}) (\\d{2}):(\\d{2}):(\\d{2}) ((\\d{4}))";
    
    public LeitorLOG(String caminho) throws FileNotFoundException, IOException{
        this.caminho = caminho;
        ler();
    }

    private void ler() throws FileNotFoundException, IOException {
       BufferedReader leitor = new BufferedReader(new FileReader(caminho));
       String linha;
       int linhaNumero = 1;
       while((linha = leitor.readLine()) != null){
           //Tipos de linhas aceitas
           //Inicio de Sessão 
           
           LocalDateTime inicio = verificarSessao(linha,REGEX_INICIO_SESSAO);
           if(inicio != null){
               System.out.println("["+linhaNumero+"]Início de sessão: "+inicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
           }
           //Fim Sessão 
           LocalDateTime fim = verificarSessao(linha,REGEX_FIM_SESSAO);
           if(fim != null){
               System.out.println("["+linhaNumero+"]Fim de sessão: "+fim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
           }
           
           linhaNumero++;
       }
       leitor.close();
    }

    private LocalDateTime verificarSessao(String linha,String regex) {
        Pattern padrao = Pattern.compile(regex);
        Matcher verificador = padrao.matcher(linha);
        int dia, mes, ano,hora, minuto,segundo;
        LocalDateTime retorno = null;
        while(verificador.find()){
            
            if(verificador.groupCount() == 7 ){
                ano = Integer.parseInt(verificador.group(7));
                
                DateTimeFormatter formatador = DateTimeFormatter.ofPattern("MMM").withLocale(Locale.ENGLISH);
                TemporalAccessor temporal = formatador.parse(verificador.group(1));
                mes = temporal.get(ChronoField.MONTH_OF_YEAR);
                
                dia = Integer.parseInt(verificador.group(2));
                
                hora = Integer.parseInt(verificador.group(3));
                
                minuto = Integer.parseInt(verificador.group(4));
                
                segundo = Integer.parseInt(verificador.group(5));
                
                retorno = LocalDateTime.of(ano, mes, dia, hora, minuto, segundo);                
            }
        }
        return retorno;
    }

    
}
