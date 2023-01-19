package com.attornatus.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

/**
 * Representação dos dados de entrada para cadastro ou atualização de um estado,
 * utilizando o padrão de projetos DTO (Data Transfer Object), evitando expor o
 * modelo do domínio.
 *
 * @author Jhansen Barreto
 */
@Getter
@Setter
@Schema(description = "Representação dos dados de entrada para cadastro ou atualização de um estado")
public class EstadoInput {

    @NotBlank
    @Pattern(regexp = "[A-Za-zÁÉÍÓÚáéíóúÇçÃÕãõÂÊÔâêô ]+") //apenas letras, letras acentuadas e espaço
    @Schema(description = "Nome do estado (apenas letras maiúsculas, minúsculas, acentuadas e espaços são permitidos)", example = "Sergipe")
    private String nome;

    @NotBlank
    @Pattern(regexp = "[A-Za-z]{2}")
    @Schema(description = "UF do estado (apenas duas letras)", example = "SE")
    private String uf;
}
