package com.attornatus.core.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe com configurações relacionadas à dependência do SpringDoc, biblioteca
 * para documentação de API's, utilizando o Swagger UI com as especificações do
 * OpenAPI 3.0
 *
 * @author Jhansen Barreto
 */
@Configuration
public class DocumentationConfig {

    /**
     * Método que insere um Bean no contexto do Spring, configurando alguns
     * detalhes da página da documentação da API.
     *
     * @return -> Nova instância de OpenAPI
     */
    @Bean
    public OpenAPI configurationDoc() {
        return new OpenAPI()
                .info(getInfoAPI());
    }

    /**
     * Método para retornar informações gerais contidas na página da
     * documentação da API.
     *
     * @return -> Nova instância de Info
     */
    private Info getInfoAPI() {
        return new Info().title("API de Gerenciamento de Pessoas e Endereços")
                .description(DESCRIPTION)
                .version("1.0")
                .contact(getContactAPI());
    }

    /**
     * Método para retornar as informações de contato contidas na página da
     * documentação da API.
     *
     * @return -> Nova instância de Contact
     */
    private Contact getContactAPI() {
        return new Contact()
                .name("Jhansen Barreto")
                .email("jhansenbarreto7@gmail.com")
                .url("http://br.linkedin.com/in/jhansen-c-barreto");
    }

    private static final String DESCRIPTION
            = "Projeto implementado para o teste técnico do processo seletivo da Attornatus Procuradoria Digital "
            + "para a vaga de Desenvolvedor Back End - Java (Júnior). API pública para gerenciar pessoas e endereços. "
            + "Afim de demonstrar também a classe de erros na documentação, exemplificando como um erro é devolvido "
            + "pela API, no endpoint de cadastro de cidades (POST) existe uma demonstração. O modelo de representação "
            + "de erros está juntamente com os demais Schemas na seção do final da página.";
}
