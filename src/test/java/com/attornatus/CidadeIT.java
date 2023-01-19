package com.attornatus;

import com.attornatus.util.BuscarArquivo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Classe de testes de integração para os endpoints relacionados a cidade.
 * Apenas alguns testes para demonstração.
 *
 * @author Jhansen Barreto
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CidadeIT {

    @LocalServerPort
    private int port;

    private String jsonCadastroUpdate;

    private static final int ID_INEXISTENTE = 0;
    private static final int ID_CIDADE_EXISTENTE = 1;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cidades";

        jsonCadastroUpdate = BuscarArquivo.getContentFile("/cidadestest/cadastro_update.json");
    }

    @Test //Status NOT_FOUND == 404
    public void statusNotFoundConsultandoCidadeInexistente() {
        RestAssured.given()
                .pathParam("id", ID_INEXISTENTE)
                .accept(ContentType.JSON)
                .when()
                .get("/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test //Status OK == 200
    public void statusOkAoEditarUmaCidade() {
        RestAssured.given()
                .body(jsonCadastroUpdate)
                .pathParam("id", ID_CIDADE_EXISTENTE)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .put("/{id}")
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
