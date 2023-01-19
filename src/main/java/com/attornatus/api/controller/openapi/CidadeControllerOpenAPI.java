package com.attornatus.api.controller.openapi;

import com.attornatus.api.exceptionhandler.Error;

import com.attornatus.api.model.dto.CidadeInput;
import com.attornatus.api.model.dto.CidadeOutput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

/**
 * Interface criada para separar a documentação da implementação do código,
 * utilizando Swagger UI com as especificações do OpenAPI 3.0
 *
 * @author Jhansen Barreto
 */
@Tag(name = "Cidades", description = "Responsável por gerenciar cidades")
public interface CidadeControllerOpenAPI {

    @Operation(summary = "Lista todas as cidades cadastradas")
    public List<CidadeOutput> listar();

    @Operation(summary = "Consulta uma cidade pelo ID (identificador)")
    public CidadeOutput buscar(
            @Parameter(
                    description = "ID de uma cidade",
                    example = "1"
            ) Long cidadeId
    );

    @ApiResponse(responseCode = "400", content = {
        @Content(schema = @Schema(implementation = Error.class))
    })
    @Operation(summary = "Cadastra uma nova cidade")
    public CidadeOutput adicionar(
            @RequestBody(
                    description = "Representação de uma nova cidade"
            ) CidadeInput input
    );

    @Operation(summary = "Atualiza uma cidade pelo ID (identificador)")
    public CidadeOutput atualizar(
            @Parameter(
                    description = "ID de uma cidade",
                    example = "1"
            ) Long cidadeId,
            @RequestBody(
                    description = "Representação de uma cidade com os novos dados"
            ) CidadeInput input
    );

    @Operation(summary = "Remove uma cidade pelo ID (identificador)")
    public void remover(
            @Parameter(
                    description = "ID de uma cidade",
                    example = "1"
            ) Long cidadeId
    );
}
