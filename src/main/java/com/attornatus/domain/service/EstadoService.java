package com.attornatus.domain.service;

import com.attornatus.domain.exception.DadosDuplicadosException;
import com.attornatus.domain.exception.EntidadeEmUsoException;
import com.attornatus.domain.exception.EntidadeNaoEncontradaException;
import com.attornatus.domain.model.Estado;
import com.attornatus.domain.repository.EstadoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Serviços associados a entidade Estado, classe do modelo do domínio.
 *
 * @author Jhansen Barreto
 */
@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    private final String MSG_NOT_FOUND = "O estado de ID '%d' não pôde ser encontrado";
    private final String MSG_CONFLICT = "O estado de ID '%d' está em uso e não pode ser removido";

    /**
     * Lista todos os estados cadastrados
     *
     * @return Lista de estados
     */
    public List<Estado> findAll() {
        return repository.findAll();
    }

    /**
     * Retorna um estado com base em um ID (identificador)
     *
     * @param id (ID do estado)
     * @return O estado do ID informado
     * @throws EntidadeNaoEncontradaException (caso não exista o estado)
     */
    public Estado findById(Long id) {
        return repository.findById(id).orElseThrow(()
                -> new EntidadeNaoEncontradaException(String.format(MSG_NOT_FOUND, id)));
    }

    /**
     * Salva um estado no banco de dados
     *
     * @param estado (entidade a ser salva)
     * @return Estado após salvo
     */
    @Transactional
    public Estado save(Estado estado) {
        estado.setNome(estado.getNome().toUpperCase());
        estado.setUf(estado.getUf().toUpperCase());

        return repository.save(estado);
    }

    /**
     * Evita o cadastro ou atualização de um estado com dados duplicados, já que
     * nome e uf são colunas únicas. Se for um novo cadastro, o parâmetro 'id'
     * deve receber o valor 'null'. Caso contrário, deve receber o ID do estado
     * em questão.
     *
     * @param id (ID do estado ou null)
     * @param nome (nome do estado)
     * @param uf (uf do estado)
     */
    public void evitaEstadoDuplicado(Long id, String nome, String uf) {
        List<Estado> estados = repository.findByNomeOrUf(nome, uf);

        if (!estados.isEmpty()) {
            //em caso de novo cadastro
            if (id == null) {
                throw new DadosDuplicadosException("Dados duplicados. Nome e/ou UF já cadastrado(s).");

            } else {
                //em caso de atualização de cadastro
                estados.forEach(item -> {
                    if (!item.getId().equals(id)) {
                        throw new DadosDuplicadosException("Dados duplicados. Nome e/ou UF já cadastrado(s).");
                    }
                });
            }
        }
    }

    /**
     * Remove um estado do banco de dados
     *
     * @param estado (estado a ser removido)
     * @throws EntidadeEmUsoException (caso a entidade esteja em uso)
     */
    @Transactional
    public void delete(Estado estado) {
        try {
            repository.delete(estado);
            repository.flush();

        } catch (DataIntegrityViolationException ex) {
            throw new EntidadeEmUsoException(String.format(MSG_CONFLICT, estado.getId()));
        }
    }
}
