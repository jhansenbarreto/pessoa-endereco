package com.attornatus.api.model.dto.id;

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
public class CidadeId {

    @NotNull
    public Long id;
}
