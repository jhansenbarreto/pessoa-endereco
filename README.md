# :pencil: Documentação

<p>
  <img src="https://img.shields.io/badge/Spring%20Boot-v3.0.1-brightgreen"/>
  <img src="https://img.shields.io/badge/java--jdk-v17.0.4.1-orange"/>
  <img src="https://img.shields.io/badge/maven--central-v4.0.0-blue"/>
  <img src="https://img.shields.io/badge/JUnit-v5.9.1-red"/>
</p>
  
>*Este projeto foi implementado na etapa do teste técnico no processo seletivo da ``Attornatus Procuradoria Digital`` para a vaga de ``Desenvolvedor Back End - Java``. Seguindo as especificações dadas no teste, o projeto é uma simples API destinada ao cadastro de Pessoas e Endereços, servindo as funcionalidades obrigatórias exigidas no teste e outras implementadas pelo desenvolvedor da API por decisão de projeto.*
  
O projeto conta com o CRUD completo das seguintes entidades: 

- ``Estados``
- ``Cidades``
- ``Pessoas``
- ``Endereços``

A documentação completa do projeto foi feita utilizando as ferramentas da biblioteca ``Spring Doc``, dependência do projeto, através do ``Swagger UI`` com as especificações do ``Open API 3.0``, afim de prover maior entendimento para os consumidores da API. Para acessar a documentação, faça o clone do projeto para a sua máquina, inicie a aplicação *(pode demorar um pouco, pois será feito o download das dependências na primeira vez)* e após subir o serviço abra seu navegador e digite: ``http://localhost:8080/swagger-ui/index.html``. Se preferir, os testes podem ser feitos na própria página da documentação, onde contém as explicações devidas para consumir cada *endpoint*.

## :hammer: Funcionalidades

:package: ``Estados:``
- Cadastra estados
- Consulta estados
- Lista todos os estados cadastrados
- Lista todas as cidades de um estado
- Edita estados
- Exclui estados
- > :bulb: **Detalhes**: *Não é permitido estados com dados duplicados, nomes só aceitam letras maiúsculas, minúsculas, acentuadas e espaços, UF só aceita 2 letras.*

:package: ``Cidades:``
- Cadastra cidades
- Consulta cidades
- Lista todas as cidades cadastradas
- Edita cidades
- Exclui cidades
- > :bulb: **Detalhes**: *Nomes só aceitam letras maiúsculas, minúsculas, acentuadas e espaços.*

:package: ``Pessoas:``
- Cadastra pessoas
- Consulta pessoas
- Lista todas as pessoas cadastradas
- Edita pessoas
- Exclui pessoas
- > :bulb: **Detalhes**: *Nomes só aceitam letras maiúsculas, minúsculas, acentuadas e espaços. Data de nascimento no padrão yyyy-mm-dd*

:package: ``Endereços:``
- Cadastra endereços para Pessoas
- Lista todos os Endereços de uma Pessoa
- Edita endereços
- Exclui endereços
- Permite marcar um endereço como principal
- > :bulb: **Detalhes**: *CEP aceita apenas 8 caracteres numéricos. Todo primeiro endereço cadastrado é marcado como principal automaticamente. Sempre que um endereço principal for excluído, caso ainda existam outros, o primeiro da lista se torna o novo principal.*

## :heavy_check_mark: Técnicas e Tecnologias Utilizadas

- ``Java 17``
- ``Spring Boot 3``
- ``Apache NetBeans IDE 15``
- ``Banco de Dados em Memória (H2)``
- ``Postman 10 (para testes práticos)``
- ``JUnit 5 (para testes programáticos)``
- ``Swagger UI / Open API 3 (para documentação)``
- ``Padrão de Projeto DTO (Data Transfer Object)``
- ``Modelagem com DDD (Domain-Driven Design)``

## :rotating_light: Observações

- ``Comentários e Javadoc:`` Se for de seu interesse gerar o Javadoc do projeto, os comentários já foram escritos para facilitar o entendimento de algumas decisões tomadas durante a implementação.
- ``Dados de Teste:`` Para facilitar os testes, foi deixado no diretório ``src/main/resources`` um arquivo ``data.sql``, utilizado para popular o banco de dados sempre que a aplicação for iniciada. Confira o arquivo <a href="https://github.com/jhansenbarreto/pessoa-endereco/blob/master/src/main/resources/data.sql">clicando aqui</a>. :bulb: **ATENÇÃO:** *Os testes de integração desenvolvidos em JUnit 5 utilizam estes dados.*
- ``Tratamento de Erros:`` A API conta com tratamento de erros e fornece ao consumidor um modelo de representação de erros baseado na especificação <a href="https://www.rfc-editor.org/rfc/rfc7807">RFC 7807 (Problem Details for HTTP APIs)</a>, utilizando-a apenas como referência, não implementando totalmente à risca.

## :construction_worker: Autor

| :technologist: Desenvolvedor |:globe_with_meridians: Links Úteis|
|-----------------------------:|----------------------------------|
|<p align="center"><img src="https://avatars.githubusercontent.com/u/13790608?v=4" width=115></br><sub>Jhansen Barreto</sub></p>|<ul><li><a href="https://github.com/jhansenbarreto?tab=repositories">GitHub</a></li><li><a href="https://br.linkedin.com/in/jhansen-c-barreto">LinkedIn</a></li><li><a href="https://www.instagram.com/jhansenbarreto/">Instagram</a></li></ul>|
