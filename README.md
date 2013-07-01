dmgame
======

Trabalho de mineração de dados referente a jogos. Faz a coleta, armazena em banco e efetua a mineração.

Egit
====
Plugin do Git para o Eclipse.

Instalação
----------
1. No eclipse, clicar em Help -> Install new software
2. No campo texto, colar o seguinte link: http://download.eclipse.org/egit/updates
3. Selecionar todos os campos e dar Next
4. Aceitar os termos do contrato (fique livre para ler =P)
5. Uma janela será aberta pedindo para reiniciar o eclipse -> Reinicie
6. Pronto.
7. 


Pegando o projeto existente
---------------------------

1. No eclipse, File -> Import -> Projects from Git e clique em Next.
2. Select a Repository Source -> URI e clique em Next.
3. Preencha os seguintes campos com os seguintes valores

Location
URI: https://github.com/atomaz/dmgame.git
Host: github.com
Repository path : /atomaz/dmgame.git (não tenho certeza, mas acho que ele preenche automaticamente)

Connection
Protocal: https

4. Clicar em next
5. Seleciona o 'master', next, next ... 
6. Após o projeto criado, clique com o botão direito no projeto, Team -> Pull (senão você não irá conseguir dar commit)




Selenium Driver
===============
Ferramenta para execução de testes automatizados me browsers. Através dele é possível manipular os elementos de uma página. 

Instalação
----------
Download do jar selenium-java-2.33.0.zip : http://code.google.com/p/selenium/downloads/list

Para colocar no projeto: 

1. Criar uma pasta dentro do projeto chamada libs
2. Colocar os jars do selenium dentro dessa pasta
2. Clicar com o botão direito em cima do projeto
2. Build Path -> Configure Build Path
3. Clicar em Add Jar
4. Selecionar todos e Ok -> Ok.


Hibernate
=========
ORM (Object Relacional Mapping). É uma ferramenta que auxilia na armazenagem dos dados no banco. Basta criar arquivos de configuração mapeando seus objetos com suas tabelas, um arquivo de configuração do hibernate, e uma classe DAO (a que faz o trabalho).

Mais informações em: http://www.hibernate.org

Ainda em fase de confguração


Postgresql
==========

Instalação
----------
1. Link para download : http://www.enterprisedb.com/products-services-training/pgdownload
2. Na instalação, irá pedir para inserir uma senha. Insira postgres
3. Criar novo banco com nome dmgame usuário postgres.

Arquivo para criação de tabelas create-tables-dmgame.sql

