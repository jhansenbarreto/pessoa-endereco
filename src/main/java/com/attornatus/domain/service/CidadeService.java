package com.attornatus.domain.service;

import com.attornatus.domain.exception.EntidadeEmUsoException;
import com.attornatus.domain.exception.EntidadeNaoEncontradaException;
import com.attornatus.domain.model.Cidade;
import com.attornatus.domain.repository.CidadeRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Serviços associados a entidade Cidade, classe do modelo do domínio.
 *
 * @author Jhansen Barreto
 */
@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    @Autowired
    private EstadoService serviceEstado;

    private final String MSG_NOT_FOUND = "A cidade de ID '%d' não pôde ser encontrada";
    private final String MSG_CONFLICT = "A cidade de ID '%d' está em uso e não pode ser removida";

    /**
     * Lista todas as cidades cadastradas
     *
     * @return Lista de cidades
     */
    public List<Cidade> findAll() {
        return repository.findAll();
    }

    /**
     * Retorna uma cidade com base em um ID (identificador)
     *
     * @param id (ID da cidade)
     * @return A cidade do ID informado
     * @throws EntidadeNaoEncontradaException (caso não exista a cidade)
     */
    public Cidade findById(Long id) {
        return repository.findById(id).orElseThrow(()
                -> new EntidadeNaoEncontradaException(String.format(MSG_NOT_FOUND, id)));
    }

    /**
     * Salva uma cidade no banco de dados
     *
     * @param cidade (entidade a ser salva)
     * @return Cidade após salva
     */
    @Transactional
    public Cidade save(Cidade cidade) {
        //verificando se o usuário passou um ID correto para estado
        var estado = serviceEstado.findById(cidade.getEstado().getId());
        cidade.setEstado(estado);

        return repository.save(cidade);
    }

    /**
     * Remove uma cidade do banco de dados
     *
     * @param cidade (cidade a ser removida)
     * @throws EntidadeEmUsoException (caso a entidade esteja em uso)
     */
    @Transactional
    public void delete(Cidade cidade) {
        try {
            repository.delete(cidade);
            repository.flush();

        } catch (DataIntegrityViolationException ex) {
            throw new EntidadeEmUsoException(String.format(MSG_CONFLICT, cidade.getId()));
        }
    }
}
