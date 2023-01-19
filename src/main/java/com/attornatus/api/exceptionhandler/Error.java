package com.attornatus.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

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
@Schema(description = "Representação de um Erro. Informações fornecidas quando existe algum problema na requisição, seja por parte do cliente ou do servidor.")
public class Error {

    @Schema(description = "Status HTTP", example = "400")
    private final Integer status;
    
    @Schema(description = "Descrição do status HTTP", example = "Bad Request")
    private final String title;
    
    @Schema(description = "Detalhe sobre o erro", example = "Um ou mais campos estão inválidos. Corrija e tente novamente.")
    private final String detail;
    
    @Schema(description = "Data/Hora do erro, no padrão Offset", example = "2023-01-18T18:14:51.4281616-03:00")
    private final OffsetDateTime timestamp;
    
    @Schema(description = "Coleção com detalhes mais específicos sobre a causa do erro")
    private final List<OriginError> errors;

    /**
     * Classe que representa a(s) origem(ns) causadora(s) do erro.
     */
    @Builder
    @Getter
    @Schema(description = "Representação da causa de um Erro")
    public static class OriginError {

        @Schema(description = "Objeto/campo causador do Erro", example = "estado.id")
        private final String name;
        
        @Schema(description = "Informação mais detalhada sobre o erro", example = "O campo é obrigatório")
        private final String message;
    }
}
