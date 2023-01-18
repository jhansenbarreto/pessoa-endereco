package com.attornatus.api.controller.openapi;

import com.attornatus.api.model.dto.EnderecoInput;
import com.attornatus.api.model.dto.EnderecoOutput;

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
@Tag(name = "Endereços de Pessoas", description = "Responsável por gerenciar endereços")
public interface PessoaEnderecoControllerOpenAPI {

    @Operation(summary = "Lista todos os endereços de uma pessoa por ID (identificador)")
    public List<EnderecoOutput> listarEnderecos(
            @Parameter(
                    description = "ID de uma pessoa",
                    example = "1"
            ) Long pessoaId
    );

    @Operation(summary = "Cadastra um novo endereço para uma pessoa por ID (identificador)")
    public EnderecoOutput adicionarEndereco(
            @Parameter(
                    description = "ID de uma pessoa",
                    example = "1"
            ) Long pessoaId,
            @RequestBody(
                    description = "Representação de um novo endereço"
            ) EnderecoInput input
    );

    @Operation(summary = "Atualiza um endereço de uma pessoa por ID's (identificadores)")
    public EnderecoOutput atualizarEndereco(
            @Parameter(
                    description = "ID de uma pessoa",
                    example = "1"
            ) Long pessoaId,
            @Parameter(
                    description = "ID de um endereço",
                    example = "1"
            ) Long enderecoId,
            @RequestBody(
                    description = "Representação de um endereço com os novos dados"
            ) EnderecoInput input
    );

    @Operation(summary = "Remove um endereço de uma pessoa por ID's (identificadores)")
    public void removerEndereco(
            @Parameter(
                    description = "ID de uma pessoa",
                    example = "1"
            ) Long pessoaId,
            @Parameter(
                    description = "ID de um endereço",
                    example = "1"
            ) Long enderecoId
    );

    @Operation(summary = "Define um endereço de uma pessoa como principal por ID's (identificadores)")
    public void escolherEnderecoPrincipal(
            @Parameter(
                    description = "ID de uma pessoa",
                    example = "1"
            ) Long pessoaId,
            @Parameter(
                    description = "ID de um endereço",
                    example = "1"
            ) Long enderecoId
    );
}
