package com.attornatus.api.controller.openapi;

import com.attornatus.api.model.dto.PessoaInput;
import com.attornatus.api.model.dto.PessoaOutput;

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
@Tag(name = "Pessoas", description = "Responsável por gerenciar pessoas")
public interface PessoaControllerOpenAPI {

    @Operation(summary = "Lista todas as pessoas cadastradas")
    public List<PessoaOutput> listar();

    @Operation(summary = "Consulta uma pessoa pelo ID (identificador)")
    public PessoaOutput buscar(
            @Parameter(
                    description = "ID de uma pessoa",
                    example = "1"
            ) Long pessoaId
    );

    @Operation(summary = "Cadastra uma nova pessoa")
    public PessoaOutput adicionar(
            @RequestBody(
                    description = "Representação de uma nova pessoa"
            ) PessoaInput input
    );

    @Operation(summary = "Atualiza uma pessoa pelo ID (identificador)")
    public PessoaOutput atualizar(
            @Parameter(
                    description = "ID de uma pessoa",
                    example = "1"
            ) Long pessoaId,
            @RequestBody(
                    description = "Representação de uma pessoa com os novos dados"
            ) PessoaInput input
    );

    @Operation(summary = "Remove uma pessoa pelo ID (identificador)")
    public void remover(
            @Parameter(
                    description = "ID de uma pessoa",
                    example = "1"
            ) Long pessoaId
    );
}
