package com.attornatus.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.OffsetDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

/**
 * Classe que representa um erro. Após a API receber alguma requisição, caso
 * ocorra algum erro no processo, seja por parte do cliente ou do servidor, a
 * API lança alguma exceção. Esta classe representa esse problema e é utilizada
 * para servir informações mais detalhadas sobre o erro ao consumidor da API.
 *
 * @author Jhansen Barreto
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class Error {

    private final Integer status;
    private final String title;
    private final String detail;
    private final OffsetDateTime timestamp;
    private final List<OriginError> errors;

    /**
     * Classe que representa a(s) origem(ns) causadora(s) do erro.
     */
    @Builder
    @Getter
    public static class OriginError {

        private final String name;
        private final String message;
    }
}
