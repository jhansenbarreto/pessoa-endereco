package com.attornatus.api.model.dto.id;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * Representação do ID de um estado, utilizada para composição de uma cidade no
 * modelo de entrada (DTO).
 *
 * @author Jhansen Barreto
 */
@Getter
@Setter
@Schema(description = "Representação dos dados de entrada para associação de estado com cidade por ID.")
public class EstadoId {

    @NotNull
    @Schema(description = "ID (identificador) do estado", example = "1")
    public Long id;
}
