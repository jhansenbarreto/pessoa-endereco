package com.attornatus.api.model.dto;

import com.attornatus.api.model.dto.id.CidadeId;
import io.swagger.v3.oas.annotations.media.Schema;

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
@Schema(description = "Representação dos dados de entrada para cadastro ou atualização de um endereço")
public class EnderecoInput {

    @NotBlank
    @Schema(description = "Descrição do endereço", example = "Rua X, Alameda dos Anjos")
    private String logradouro;

    @NotBlank
    @Pattern(regexp = "[0-9]{8}")
    @Schema(description = "CEP do endereço, apenas números (um CEP é composto por 8 dígitos)", example = "44999000")
    private String cep;

    @NotBlank
    @Schema(description = "Número do endereço", example = "1500")
    private String numero;

    @Valid
    @NotNull
    @Schema(description = "Representação do ID de uma cidade")
    public CidadeId cidade;
}
