package com.attornatus.domain.service;

import com.attornatus.domain.exception.EntidadeNaoEncontradaException;
import com.attornatus.domain.model.Pessoa;
import com.attornatus.domain.repository.PessoaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Serviços associados a entidade Pessoa, classe do modelo do domínio
 *
 * @author Jhansen Barreto
 */
@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    private final String MSG_NOT_FOUND = "A pessoa de ID '%d' não pôde ser encontrada";

    /**
     * Lista todas as pessoas cadastradas
     *
     * @return Lista de pessoas
     */
    public List<Pessoa> findAll() {
        return repository.findAll();
    }

    /**
     * Retorna uma pessoa com base em um ID (identificador)
     *
     * @param id (ID da pessoa)
     * @return A pessoa do ID informado
     * @throws EntidadeNaoEncontradaException (caso não exista a pessoa)
     */
    public Pessoa findById(Long id) {
        return repository.findById(id).orElseThrow(()
                -> new EntidadeNaoEncontradaException(String.format(MSG_NOT_FOUND, id)));
    }

    /**
     * Salva uma pessoa no banco de dados
     *
     * @param pessoa (entidade a ser salva)
     * @return Pessoa após salva
     */
    @Transactional
    public Pessoa save(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    /**
     * Remove uma pessoa do banco de dados
     *
     * @param pessoa (pessoa a ser removida)
     */
    @Transactional
    public void removerPessoa(Pessoa pessoa) {
        //por causa do atributo 'endereços' estar marcado com CascadeType.REMOVE,
        //todos os endereços de uma pessoa são excluídos juntamente com a pessoa
        repository.delete(pessoa);
    }
}
