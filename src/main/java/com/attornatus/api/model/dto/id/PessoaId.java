package com.attornatus.api.model.dto.id;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * Representação do ID de uma pessoa, utilizada para composição de um endereço
 * no modelo de entrada (DTO).
 *
 * @author Jhansen Barreto
 */
@Getter
@Setter
@Schema(description = "Representação dos dados de entrada para associação de pessoa com endereço por ID.")
public class PessoaId {

    @NotNull
    @Schema(description = "ID (identificador) da pessoa", example = "1")
    public Long id;
}
