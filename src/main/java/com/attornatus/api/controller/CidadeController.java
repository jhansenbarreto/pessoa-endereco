package com.attornatus.api.controller;

import com.attornatus.api.assembler.CidadeConvert;
import com.attornatus.api.controller.openapi.CidadeControllerOpenAPI;
import com.attornatus.api.model.dto.CidadeInput;
import com.attornatus.api.model.dto.CidadeOutput;
import com.attornatus.domain.service.CidadeService;

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
@RequestMapping("/cidades")
public class CidadeController implements CidadeControllerOpenAPI {

    @Autowired
    private CidadeService service;

    @Autowired
    private CidadeConvert convert;

    @Override
    @GetMapping
    public List<CidadeOutput> listar() {
        var cidades = service.findAll();
        return convert.toListOutput(cidades);
    }

    @Override
    @GetMapping("/{cidadeId}")
    public CidadeOutput buscar(@PathVariable Long cidadeId) {
        var cidade = service.findById(cidadeId);
        return convert.toOutput(cidade);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeOutput adicionar(@RequestBody @Valid CidadeInput input) {
        var cidade = convert.toDomainModel(input);
        cidade = service.save(cidade);

        return convert.toOutput(cidade);
    }

    @Override
    @PutMapping("/{cidadeId}")
    public CidadeOutput atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInput input) {
        var cidade = service.findById(cidadeId);
        convert.copy(input, cidade);
        service.save(cidade);

        return convert.toOutput(cidade);
    }

    @Override
    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        var cidade = service.findById(cidadeId);
        service.delete(cidade);
    }
}
