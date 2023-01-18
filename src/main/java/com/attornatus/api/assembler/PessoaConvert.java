package com.attornatus.api.assembler;

import com.attornatus.api.model.dto.PessoaInput;
import com.attornatus.api.model.dto.PessoaOutput;
import com.attornatus.domain.model.Pessoa;

import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe dedicada a conversão de objetos do modelo do domínio e modelos de
 * entrada e saída de dados (padrão DTO) de Pessoa.
 *
 * @author Jhansen Barreto
 */
@Component
public class PessoaConvert {

    @Autowired
    private ModelMapper mapper;

    public PessoaOutput toOutput(Pessoa domainModel) {
        return mapper.map(domainModel, PessoaOutput.class);
    }

    public List<PessoaOutput> toListOutput(Collection<Pessoa> collection) {
        return collection.stream().map(object -> toOutput(object)).toList();
    }

    public Pessoa toDomainModel(PessoaInput input) {
        return mapper.map(input, Pessoa.class);
    }

    public void copy(PessoaInput input, Pessoa domainModel) {
        mapper.map(input, domainModel);
    }
}
