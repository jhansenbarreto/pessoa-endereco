package com.attornatus.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Representação dos dados de saída de uma pessoa, utilizando o padrão de
 * projetos DTO (Data Transfer Object), evitando expor o modelo do domínio.
 *
 * @author Jhansen Barreto
 */
@Getter
@Setter
@Schema(description = "Representação de uma pessoa fornecida na solicitação do recurso.")
public class PessoaOutput {
    
    @Schema(description = "ID (identificador) da pessoa", example = "1")
    private Long id;
    
    @Schema(description = "Nome da pessoa", example = "Maria Joaquina")
    private String nome;
    
    @Schema(description = "Data de nascimento da pessoa", example = "1993-07-12")
    private Date dataNascimento;
    
    /*
        Esta classe possuía uma lista de endereços, mas o atributo foi removido 
        para os endereços da pessoa serem consultados apenas no endpoint específico
        para essa solicitação.
    */
}
