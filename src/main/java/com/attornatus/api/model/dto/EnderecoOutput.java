package com.attornatus.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * Representação dos dados de saída de um endereço, utilizando o padrão de
 * projetos DTO (Data Transfer Object), evitando expor o modelo do domínio.
 *
 * @author Jhansen Barreto
 */
@Getter
@Setter
@Schema(description = "Representação de um endereço fornecido na solicitação do recurso.")
public class EnderecoOutput {

    @Schema(description = "ID (identificador) do endereço", example = "1")
    public Long id;
    
    @Schema(description = "Informação se o endereço é o principal ou não", example = "false")
    public Boolean principal;
    
    @Schema(description = "Descrição do endereço", example = "Rua X, Alameda dos Anjos")
    public String logradouro;
    
    @Schema(description = "CEP do endereço", example = "44999000")
    public String cep;
    
    @Schema(description = "Número do endereço", example = "1500")
    public String numero;
    
    @Schema(description = "Nome/UF da cidade", example = "Aracaju-SE")
    public String cidade;
}
