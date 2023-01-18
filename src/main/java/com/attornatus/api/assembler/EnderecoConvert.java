package com.attornatus.api.assembler;

import com.attornatus.api.model.dto.EnderecoInput;
import com.attornatus.api.model.dto.EnderecoOutput;
import com.attornatus.domain.model.Cidade;
import com.attornatus.domain.model.Endereco;

import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe dedicada a conversão de objetos do modelo do domínio e modelos de
 * entrada e saída de dados (padrão DTO) de Endereco.
 *
 * @author Jhansen Barreto
 */
@Component
public class EnderecoConvert {

    @Autowired
    private ModelMapper mapper;

    public EnderecoOutput toOutput(Endereco domainModel) {
        return mapper.map(domainModel, EnderecoOutput.class);
    }

    public List<EnderecoOutput> toListOutput(Collection<Endereco> collection) {
        return collection.stream().map(object -> toOutput(object)).toList();
    }

    public Endereco toDomainModel(EnderecoInput input) {
        return mapper.map(input, Endereco.class);
    }

    public void copy(EnderecoInput input, Endereco domainModel) {
        //para evitar org.springframework.orm.jpa.JpaSystemException
        domainModel.setCidade(new Cidade());
        
        mapper.map(input, domainModel);
    }
}
