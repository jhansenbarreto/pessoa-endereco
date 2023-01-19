package com.attornatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.hamcrest.Matchers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Classe de testes de integração para os endpoints relacionados a pessoa.
 * Apenas alguns testes para demonstração.
 *
 * @author Jhansen Barreto
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PessoaIT {

    @LocalServerPort
    private int port;

    private static final int ID_1_PESSOA_EXISTENTE = 1;
    private static final String NOME_1_PESSOA_EXISTENTE = "Peter Parker";

    private static final int ID_2_PESSOA_EXISTENTE = 2;
    
    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/pessoas";
    }

    @Test //Status OK == 200
    public void statusOkNaConsultaComConteudoCorreto() {
        RestAssured.given()
                .pathParam("id", ID_1_PESSOA_EXISTENTE)
                .accept(ContentType.JSON)
                .when()
                .get("/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", Matchers.equalTo(NOME_1_PESSOA_EXISTENTE));
    }
    
    @Test //Status NO_CONTENT == 204
    public void statusNoContentAoExcluirPessoaExistente() {
        RestAssured.given()
                .pathParam("id", ID_2_PESSOA_EXISTENTE)
                .accept(ContentType.JSON)
                .when()
                .delete("/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
}
