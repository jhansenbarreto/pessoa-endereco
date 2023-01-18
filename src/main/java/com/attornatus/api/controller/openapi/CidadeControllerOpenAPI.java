package com.attornatus.api.controller.openapi;

import com.attornatus.api.model.dto.CidadeInput;
import com.attornatus.api.model.dto.CidadeOutput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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