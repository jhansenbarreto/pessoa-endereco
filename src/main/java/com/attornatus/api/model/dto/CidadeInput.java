package com.attornatus.api.model.dto;

import com.attornatus.api.model.dto.id.EstadoId;

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
public class CidadeInput {

    @NotBlank
    @Pattern(regexp = "[A-Za-zÁÉÍÓÚáéíóúÇçÃÕãõÂÊÔâêô ]+") //apenas letras, letras acentuadas e espaço
    public String nome;

    @Valid
    @NotNull
    public EstadoId estado;
}
