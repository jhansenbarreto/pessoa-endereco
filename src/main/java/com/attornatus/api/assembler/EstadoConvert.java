package com.attornatus.api.assembler;

import com.attornatus.api.model.dto.EstadoInput;
import com.attornatus.api.model.dto.EstadoOutput;
import com.attornatus.domain.model.Estado;

import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe dedicada a conversão de objetos do modelo do domínio e modelos de
 * entrada e saída de dados (padrão DTO) de Estado.
 *
 * @author Jhansen Barreto
 */
@Component
public class EstadoConvert {

    @Autowired
    private ModelMapper mapper;

    public EstadoOutput toOutput(Estado domainModel) {
        return mapper.map(domainModel, EstadoOutput.class);
    }

    public List<EstadoOutput> toListOutput(Collection<Estado> collection) {
        return collection.stream().map(object -> toOutput(object)).toList();
    }

    public Estado toDomainModel(EstadoInput input) {
        return mapper.map(input, Estado.class);
    }

    public void copy(EstadoInput input, Estado domainModel) {
        mapper.map(input, domainModel);
    }
}
