package com.attornatus.api.controller;

import com.attornatus.api.assembler.PessoaConvert;
import com.attornatus.api.controller.openapi.PessoaControllerOpenAPI;
import com.attornatus.api.model.dto.PessoaInput;
import com.attornatus.api.model.dto.PessoaOutput;
import com.attornatus.domain.service.PessoaService;

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
@RequestMapping("/pessoas")
public class PessoaController implements PessoaControllerOpenAPI {

    @Autowired
    private PessoaService service;

    @Autowired
    private PessoaConvert convert;

    @Override
    @GetMapping
    public List<PessoaOutput> listar() {
        var pessoas = service.findAll();

        return convert.toListOutput(pessoas);
    }

    @Override
    @GetMapping("/{pessoaId}")
    public PessoaOutput buscar(@PathVariable Long pessoaId) {
        var pessoa = service.findById(pessoaId);

        return convert.toOutput(pessoa);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PessoaOutput adicionar(@RequestBody @Valid PessoaInput input) {
        var pessoa = convert.toDomainModel(input);
        pessoa = service.save(pessoa);

        return convert.toOutput(pessoa);
    }

    @Override
    @PutMapping("/{pessoaId}")
    public PessoaOutput atualizar(@PathVariable Long pessoaId, @RequestBody @Valid PessoaInput input) {
        var pessoa = service.findById(pessoaId);

        convert.copy(input, pessoa);
        pessoa = service.save(pessoa);

        return convert.toOutput(pessoa);
    }

    @Override
    @DeleteMapping("/{pessoaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long pessoaId) {
        var pessoa = service.findById(pessoaId);
        service.removerPessoa(pessoa);
    }
}
