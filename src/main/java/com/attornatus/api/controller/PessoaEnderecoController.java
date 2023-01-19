package com.attornatus.api.controller;

import com.attornatus.api.assembler.EnderecoConvert;
import com.attornatus.api.controller.openapi.PessoaEnderecoControllerOpenAPI;
import com.attornatus.api.model.dto.EnderecoInput;
import com.attornatus.api.model.dto.EnderecoOutput;

import com.attornatus.domain.service.EnderecoService;
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
@RequestMapping("/pessoas/{pessoaId}/enderecos")
public class PessoaEnderecoController implements PessoaEnderecoControllerOpenAPI {

    @Autowired
    private PessoaService servicePessoa;

    @Autowired
    private EnderecoService serviceEndereco;

    @Autowired
    private EnderecoConvert convert;

    @Override
    @GetMapping
    public List<EnderecoOutput> listarEnderecos(@PathVariable Long pessoaId) {
        var pessoa = servicePessoa.findById(pessoaId);
        return convert.toListOutput(pessoa.getEnderecos());
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoOutput adicionarEndereco(@PathVariable Long pessoaId, @RequestBody @Valid EnderecoInput input) {
        var pessoa = servicePessoa.findById(pessoaId);
        var endereco = convert.toDomainModel(input);

        endereco.setPessoa(pessoa);
        endereco = serviceEndereco.save(endereco);

        return convert.toOutput(endereco);
    }

    @Override
    @PutMapping("/{enderecoId}")
    public EnderecoOutput atualizarEndereco(
            @PathVariable Long pessoaId, @PathVariable Long enderecoId, @RequestBody @Valid EnderecoInput input) {

        //consulta para verificar a existência da pessoa com o ID informado
        var pessoa = servicePessoa.findById(pessoaId);
        var endereco = serviceEndereco.findById(enderecoId, pessoa.getId());

        convert.copy(input, endereco);
        endereco = serviceEndereco.save(endereco);

        return convert.toOutput(endereco);
    }

    @Override
    @DeleteMapping("/{enderecoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerEndereco(@PathVariable Long pessoaId, @PathVariable Long enderecoId) {

        //consulta para verificar a existência da pessoa com o ID informado
        var pessoa = servicePessoa.findById(pessoaId);
        var endereco = serviceEndereco.findById(enderecoId, pessoa.getId());
        var principal = endereco.getPrincipal();

        serviceEndereco.removerEndereco(endereco);

        //se o endereço excluído for um principal, um novo é eleito
        if (principal) {
            serviceEndereco.elegerNovoPrincipal(pessoa.getEnderecos());
        }
    }

    @Override
    @PutMapping("/{enderecoId}/principal")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void escolherEnderecoPrincipal(@PathVariable Long pessoaId, @PathVariable Long enderecoId) {

        //consulta para verificar a existência da pessoa com o ID informado
        var pessoa = servicePessoa.findById(pessoaId);
        var endereco = serviceEndereco.findById(enderecoId, pessoa.getId());

        serviceEndereco.escolherEnderecoPrincipal(endereco);
    }
}
