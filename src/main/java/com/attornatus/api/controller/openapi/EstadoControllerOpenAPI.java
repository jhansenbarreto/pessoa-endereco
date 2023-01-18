package com.attornatus.api.controller.openapi;

import com.attornatus.api.model.dto.CidadeOutput;
import com.attornatus.api.model.dto.EstadoInput;
import com.attornatus.api.model.dto.EstadoOutput;

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
@Tag(name = "Estados", description = "Responsável por gerenciar estados")
public interface EstadoControllerOpenAPI {

    @Operation(summary = "Lista todos os estados cadastrados")
    public List<EstadoOutput> listar();
    
    @Operation(summary = "Lista todas as cidades de um estado pelo ID (identificador)")
    public List<CidadeOutput> listarCidades(Long estadoId);

    @Operation(summary = "Consulta um estado pelo ID (identificador)")
    public EstadoOutput buscar(
            @Parameter(
                    description = "ID de um estado",
                    example = "1"
            ) Long estadoId
    );

    @Operation(summary = "Cadastra um novo estado")
    public EstadoOutput adicionar(
            @RequestBody(
                    description = "Representação de um novo estado"
            ) EstadoInput input
    );

    @Operation(summary = "Atualiza um estado pelo ID (identificador)")
    public EstadoOutput atualizar(
            @Parameter(
                    description = "ID de um estado",
                    example = "1"
            ) Long estadoId,
            @RequestBody(
                    description = "Representação de um estado com os novos dados"
            ) EstadoInput input
    );

    @Operation(summary = "Remove um estado pelo ID (identificador)")
    public void remover(
            @Parameter(
                    description = "ID de um estado",
                    example = "1"
            ) Long estadoId
    );
}