package com.attornatus.api.model.dto;

import com.attornatus.api.model.dto.id.CidadeId;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

/**
 * Representação dos dados de entrada para cadastro ou atualização de um
 * endereço, utilizando o padrão de projetos DTO (Data Transfer Object),
 * evitando expor o modelo do domínio.
 *
 * @author Jhansen Barreto
 */
@Getter
@Setter
public class EnderecoInput {

    @NotBlank
    public String logradouro;

    @NotBlank
    @Pattern(regexp = "[0-9]{8}")
    public String cep;

    @NotBlank
    public String numero;

    @Valid
    @NotNull
    public CidadeId cidade;
}
