package com.attornatus.api.model.dto.id;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * Representação do ID de uma cidade, utilizada para composição de um endereço
 * no modelo de entrada (DTO).
 *
 * @author Jhansen Barreto
 */
@Getter
@Setter
@Schema(description = "Representação dos dados de entrada para associação de cidade com endereço por ID.")
public class CidadeId {

    @NotNull
    @Schema(description = "ID (identificador) da cidade", example = "1")
    public Long id;
}
