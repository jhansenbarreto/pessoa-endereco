package com.attornatus.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Representação dos dados de entrada para cadastro ou atualização de uma
 * pessoa, utilizando o padrão de projetos DTO (Data Transfer Object), evitando
 * expor o modelo do domínio.
 *
 * @author Jhansen Barreto
 */
@Getter
@Setter
@Schema(description = "Representação dos dados de entrada para cadastro ou atualização de uma pessoa")
public class PessoaInput {

    @NotBlank
    @Pattern(regexp = "[A-Za-zÁÉÍÓÚáéíóúÇçÃÕãõÂÊÔâêô ]+") //apenas letras, letras acentuadas e espaço
    @Schema(description = "Nome da pessoa (apenas letras maiúsculas, minúsculas, acentuadas e espaços são permitidos)", example = "Maria Joaquina")
    private String nome;

    @NotNull
    @Schema(description = "Data de nascimento da pessoa, no padrão yyyy-mm-dd", example = "1993-07-12")
    private Date dataNascimento;
}
