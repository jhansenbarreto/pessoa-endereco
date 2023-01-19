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
 * Classe de testes de integração para os endpoints relacionados a estado.
 * Apenas alguns testes para demonstração.
 *
 * @author Jhansen Barreto
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EstadoIT {

    @LocalServerPort
    private int port;

    private String jsonCadastroCorreto;
    private String jsonCadastroIncorreto;

    private static final int ID_INEXISTENTE = 0;
    private static final int ID_ESTADO_EM_USO = 1;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/estados";

        jsonCadastroCorreto = BuscarArquivo.getContentFile("/estadostest/cadastro_correto.json");
        jsonCadastroIncorreto = BuscarArquivo.getContentFile("/estadostest/cadastro_incorreto.json");
    }

    @Test //Status OK == 200
    public void statusOkNaConsulta() {
        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test //Status CREATED == 201
    public void statusCreatedAoSalvar() {
        RestAssured.given()
                .body(jsonCadastroCorreto)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test //Status BAD_REQUEST == 400
    public void statusBadRequestAoTentarSalvarDadosInvalidos() {
        RestAssured.given()
                .body(jsonCadastroIncorreto)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test //Status NOT_FOUND == 404
    public void statusNotFoundConsultandoEstadoInexistente() {
        RestAssured.given()
                .pathParam("id", ID_INEXISTENTE)
                .accept(ContentType.JSON)
                .when()
                .get("/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test //Status CONFLICT == 409
    public void statusConflictExcluindoEstadoEmUso() {
        RestAssured.given()
                .pathParam("id", ID_ESTADO_EM_USO)
                .accept(ContentType.JSON)
                .when()
                .delete("/{id}")
                .then()
                .statusCode(HttpStatus.CONFLICT.value());
    }
}
