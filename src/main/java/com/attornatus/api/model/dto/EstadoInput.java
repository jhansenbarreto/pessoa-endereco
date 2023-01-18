package com.attornatus.api.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

/**
 * Representação dos dados de entrada para cadastro ou atualização de um
 * estado, utilizando o padrão de projetos DTO (Data Transfer Object),
 * evitando expor o modelo do domínio.
 *
 * @author Jhansen Barreto
 */
@Getter
@Setter
public class EstadoInput {

    @NotBlank
    @Pattern(regexp = "[A-Za-zÁÉÍÓÚáéíóúÇçÃÕãõÂÊÔâêô ]+") //apenas letras, letras acentuadas e espaço
    public String nome;

    @NotBlank
    @Pattern(regexp = "[A-Za-z]{2}")
    public String uf;
}
