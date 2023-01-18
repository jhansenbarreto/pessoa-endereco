package com.attornatus.api.assembler;

import com.attornatus.api.model.dto.CidadeInput;
import com.attornatus.api.model.dto.CidadeOutput;
import com.attornatus.domain.model.Cidade;
import com.attornatus.domain.model.Estado;

import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe dedicada a conversão de objetos do modelo do domínio e modelos de
 * entrada e saída de dados (padrão DTO) de Cidade.
 *
 * @author Jhansen Barreto
 */
@Component
public class CidadeConvert {

    @Autowired
    private ModelMapper mapper;

    public CidadeOutput toOutput(Cidade domainModel) {
        return mapper.map(domainModel, CidadeOutput.class);
    }

    public List<CidadeOutput> toListOutput(Collection<Cidade> collection) {
        return collection.stream().map(object -> toOutput(object)).toList();
    }

    public Cidade toDomainModel(CidadeInput input) {
        return mapper.map(input, Cidade.class);
    }

    public void copy(CidadeInput input, Cidade domainModel) {
        //para evitar org.springframework.orm.jpa.JpaSystemException
        domainModel.setEstado(new Estado());

        mapper.map(input, domainModel);
    }
}
