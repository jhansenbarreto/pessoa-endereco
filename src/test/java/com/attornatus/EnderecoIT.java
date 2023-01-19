package com.attornatus;

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
 * Classe de testes de integração para os endpoints relacionados a endereço.
 * Apenas alguns testes para demonstração.
 *
 * @author Jhansen Barreto
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EnderecoIT {

    @LocalServerPort
    private int port;

    private static final int ID_PESSOA_EXISTENTE_COM_VARIOS_ENDERECOS = 3;
    private static final int ID_ENDERECO_ESCOLHIDO_COMO_PRINCIPAL = 4;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath
                = String.format("/pessoas/%d/enderecos/%d/principal",
                        ID_PESSOA_EXISTENTE_COM_VARIOS_ENDERECOS,
                        ID_ENDERECO_ESCOLHIDO_COMO_PRINCIPAL);
    }

    @Test //Status NO_CONTENT == 204
    public void statusNoContentAoEscolherEnderecoPrincipal() {
        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .put()
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
}
