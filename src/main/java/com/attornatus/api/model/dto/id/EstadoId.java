package com.attornatus.api.model.dto.id;

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
public class EstadoId {

    @NotNull
    public Long id;
}
