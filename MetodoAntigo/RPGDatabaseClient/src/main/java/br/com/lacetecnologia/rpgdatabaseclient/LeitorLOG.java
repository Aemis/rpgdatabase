package br.com.lacetecnologia.rpgdatabaseclient;

import br.com.lacetecnologia.rpgdatabaseclient.estrutura.Acao;
import br.com.lacetecnologia.rpgdatabaseclient.estrutura.Conversa;
import br.com.lacetecnologia.rpgdatabaseclient.estrutura.Jogo;
import br.com.lacetecnologia.rpgdatabaseclient.estrutura.Sessao;
import br.com.lacetecnologia.rpgdatabaseclient.estrutura.TipoAcao;
import java.io.BufferedReader;
import java.io.File;
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
    private final String REGEX_CONVERSAS = "(?:.+|)\\[(\\d{2}):(\\d{2}):(\\d{2})\\] <(?:@|)(\\w+)> ((.+))";
    private final String REGEX_FIM_SESSAO = "(?:\\*\\*\\*\\* (?:REGISTRO FINALIZANDO EM|REGISTO TERMINADO EM|ENDING LOGGING AT)|Session Close:) \\w{3} (\\w{3}) (\\d{2}| \\d{1}) (\\d{2}):(\\d{2}):(\\d{2}) ((\\d{4}))";
    private Jogo jogo;
    
    public LeitorLOG(String caminho) throws FileNotFoundException, IOException{
        File arquivo = new File(caminho);
        if(arquivo.exists()){
            this.caminho = caminho;
            jogo = new Jogo(arquivo.getName().replace(".log", "").replace("#", ""));
            ler();
        }
        
    }

    private void ler() throws FileNotFoundException, IOException {
       BufferedReader leitor = new BufferedReader(new FileReader(caminho));
       String linha;
       Sessao sessao = null;
       Acao fato = null;
       int linhaNumero = 1;
       while((linha = leitor.readLine()) != null){
           //Tipos de linhas aceitas
           
           //Inicio de Sessão 
           LocalDateTime inicio = verificarSessao(linha,REGEX_INICIO_SESSAO);
           if(inicio != null){
               sessao = new Sessao();
               sessao.setInicio(inicio);
           }
           
           
           //Conversas
           if(inicio == null && sessao != null){
               
               Conversa conversa = verificarConversa(sessao.getInicio(), linha);
               if(conversa != null){
                    sessao.addJogador(conversa.getJogador());
                    fato = new Acao();
                    fato.setDataHora(conversa.getTimestamp());
                    fato.setQuem(conversa.getJogador());
                    fato.setTipo(verificaTipoAcao(conversa.getFrase()));
                    fato.setDescricao(conversa.getFrase());
                    
                    sessao.getFatos().add(fato);
             }
           }
           
           //Fim Sessão 
            LocalDateTime fim = verificarSessao(linha,REGEX_FIM_SESSAO);
            if(fim != null){
                 sessao.setFim(fim);
                 jogo.getSessoes().add(sessao);
                 sessao = null;
            }
           
           linhaNumero++;
       }
       leitor.close();
       LocalDateTime primeiroInicio = null, ultimoFim = null;
       for(Sessao session: jogo.getSessoes()){  
           if(primeiroInicio == null) primeiroInicio = session.getInicio();
           if(ultimoFim == null) ultimoFim = session.getFim();
           
           if(session.getFim().isAfter(ultimoFim)) ultimoFim = session.getFim();
           if(session.getInicio().isBefore(primeiroInicio)) primeiroInicio = session.getInicio();
           
           jogo.addJogadores(session.getJogadores());
       }
       jogo.setInicio(primeiroInicio);
       jogo.setFim(ultimoFim);
        System.out.println(jogo);
       
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
                TemporalAccessor temporal = formatador.parse(verificador.group(1).trim());
                mes = temporal.get(ChronoField.MONTH_OF_YEAR);
                
                dia = Integer.parseInt(verificador.group(2).trim());
                
                hora = Integer.parseInt(verificador.group(3).trim());
                
                minuto = Integer.parseInt(verificador.group(4).trim());
                
                segundo = Integer.parseInt(verificador.group(5).trim());
                
                retorno = LocalDateTime.of(ano, mes, dia, hora, minuto, segundo);                
            }
        }
        return retorno;
    }

    private Conversa verificarConversa(LocalDateTime inicio, String linha) {
        Pattern padrao = Pattern.compile(REGEX_CONVERSAS);
        Matcher verificador = padrao.matcher(linha);
        
        Conversa conversa = null;
        while(verificador.find()){
            
            if(verificador.groupCount() == 6 ){

                LocalDateTime timestamp = LocalDateTime.of(inicio.getYear(), inicio.getMonth(), inicio.getDayOfMonth(),  
                           Integer.parseInt(verificador.group(1)),  Integer.parseInt(verificador.group(2)),  Integer.parseInt(verificador.group(3)));
                String jogador =  verificador.group(4);
                String frase = verificador.group(5);
                conversa = new Conversa(timestamp,jogador,frase);
            }
        }
        return conversa;
    }
    
    private TipoAcao verificaTipoAcao(String frase){
        if(frase.matches("(- .+)")){
            return TipoAcao.FALA;
        }
        else if(frase.matches("\\[\\d{2}:\\d{2}(:\\d{2}|)\\] <\\w+>.+")){
            return TipoAcao.FLASHBACK;
        }
        else if(frase.matches("\".+\"")){
            return TipoAcao.PENSAMENTO;
        }else{
            return TipoAcao.ATITUDE;
        }
        
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }
    
    
}

