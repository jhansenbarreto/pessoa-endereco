package com.attornatus.core.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração que expõe um Bean para o contexto do Spring,
 * permitindo a injeção de dependência para uma instância de ModelMapper
 *
 * @author Jhansen Barreto
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
