# Nome do Projeto: SPE (spe_codigo_fonte) #

• Sistema de Profissionais e Estabelecimentos.


# Sistema 
• O SPE é um sistema desenvolvido com Java 8, SpringBoot métodos rest e angular 9


# PostgreSQL 
• Banco de dados PostgreSQL


# Serviços 
• Serviços da solução são APIs REST utilizadas para consumo e geração de dados dentro dos modelos e componentes.


# Spebackend 
• É o serviço responsável por gerenciar profissionais, estabelecimentos e seus vínculos.


# Ambiente de desenvolvimento 
Compose dos containers
• Navegue até a pasta /docker e execute: [ docker-compose -f docker-compose-dev.yml up -d ] Os containers serão construídos e estarão prontos.


# Serviços
• Os serviços são compilados com o maven. execute: [ mvn clean install ] e em seguida: [ mvn spring-boot:run ]


# Cliente
• O cliente REST da aplicação é desenvolvido em angular 9 e utiliza a biblioteca do PrimeNG como principal fonte de componentes. Vá na pasta /spe_frontend e execute: [ npm install ] e em seguida: [ npm start ] O sistema estará disponível e operante na URL localhost:4200
