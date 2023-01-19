package com.attornatus.api.model.dto;

import com.attornatus.api.model.dto.id.EstadoId;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

/**
 * Representação dos dados de entrada para cadastro ou atualização de uma
 * cidade, utilizando o padrão de projetos DTO (Data Transfer Object), evitando
 * expor o modelo do domínio.
 *
 * @author Jhansen Barreto
 */
@Getter
@Setter
@Schema(description = "Representação dos dados de entrada para cadastro ou atualização de uma cidade")
public class CidadeInput {

    @NotBlank
    @Pattern(regexp = "[A-Za-zÁÉÍÓÚáéíóúÇçÃÕãõÂÊÔâêô ]+") //apenas letras, letras acentuadas e espaço
    @Schema(description = "Nome da cidade (apenas letras maiúsculas, minúsculas, acentuadas e espaços são permitidos)", example = "Aracaju")
    private String nome;

    @Valid
    @NotNull
    @Schema(description = "Representação do ID de um estado")
    private EstadoId estado;
}
