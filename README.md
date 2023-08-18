# RPG-Database
Programa de leitura e exibição de logs em uma plataforma WEB. 
Desenvolvido em JAVA em duas versões: 
- Modelo Antigo: JSF+JPA Usando tudo o que sei até o momento;
- Modelo Novo: JAVA17 utilizando novas tecnologias que estou estudando;

#PROBLEMA: 
Sincronizar logs de vários programas de IRC distintos. O programa dever:
- A partir de uma pasta base, entrar em todas as sub-pastas procurando arquivos .log e registrar em uma tabela de arquivos com um identificador único para o arquivo;
- Em cada arquivo deverão ser identificados, o nome do jogo, a lista dos jogadores, a lista de personagens e NPCs utilizados, as sessões que ocorreram os jogos e o texto com o conteúdo do jogo identificado por data/hora;
- Os dados referentes à cada jogo deverão ser armazenados em um banco de dados; 
- É possível ler todo o conteúdo de um determinado jogo em uma página web, acessíve após login e senha; 

#Modelo Antigo 
Utilizando:
- Netbeans IDE 
- Java 1.8.241
- Postgres 14.9
- JSF
- Payara Server

