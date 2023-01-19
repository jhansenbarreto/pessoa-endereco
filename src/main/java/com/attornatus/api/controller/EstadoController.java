package com.attornatus.api.controller;

import com.attornatus.api.assembler.CidadeConvert;
import com.attornatus.api.assembler.EstadoConvert;
import com.attornatus.api.controller.openapi.EstadoControllerOpenAPI;
import com.attornatus.api.model.dto.CidadeOutput;
import com.attornatus.api.model.dto.EstadoInput;
import com.attornatus.api.model.dto.EstadoOutput;
import com.attornatus.domain.service.EstadoService;

import jakarta.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jhansen Barreto
 */
@RestController
@RequestMapping("/estados")
public class EstadoController implements EstadoControllerOpenAPI {

    @Autowired
    private EstadoService service;

    @Autowired
    private EstadoConvert convertEstado;

    @Autowired
    private CidadeConvert convertCidade;

    @Override
    @GetMapping
    public List<EstadoOutput> listar() {
        var estados = service.findAll();
        return convertEstado.toListOutput(estados);
    }

    @Override
    @GetMapping("/{estadoId}/cidades")
    public List<CidadeOutput> listarCidades(@PathVariable Long estadoId) {
        var estado = service.findById(estadoId);
        return convertCidade.toListOutput(estado.getCidades());
    }

    @Override
    @GetMapping("/{estadoId}")
    public EstadoOutput buscar(@PathVariable Long estadoId) {
        var estado = service.findById(estadoId);
        return convertEstado.toOutput(estado);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoOutput adicionar(@RequestBody @Valid EstadoInput input) {
        service.evitaEstadoDuplicado(null, input.getNome().toUpperCase(), input.getUf().toUpperCase());
        
        var estado = convertEstado.toDomainModel(input);
        estado = service.save(estado);

        return convertEstado.toOutput(estado);
    }

    @Override
    @PutMapping("/{estadoId}")
    public EstadoOutput atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput input) {
        var estado = service.findById(estadoId);        
        service.evitaEstadoDuplicado(estadoId, input.getNome().toUpperCase(), input.getUf().toUpperCase());
        
        convertEstado.copy(input, estado);
        service.save(estado);

        return convertEstado.toOutput(estado);
    }

    @Override
    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId) {
        var estado = service.findById(estadoId);
        service.delete(estado);
    }
}
