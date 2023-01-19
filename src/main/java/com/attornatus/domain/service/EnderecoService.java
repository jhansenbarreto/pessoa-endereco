package com.attornatus.domain.service;

import com.attornatus.domain.exception.EntidadeNaoEncontradaException;
import com.attornatus.domain.model.Endereco;
import com.attornatus.domain.repository.EnderecoRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Serviços associados a entidade Endereco, classe do modelo do domínio.
 *
 * @author Jhansen Barreto
 */
@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private CidadeService serviceCidade;

    private final String MSG_NOT_FOUND = "A pessoa de ID '%d' não possui um endereco de ID '%d'";

    /**
     * Retorna um endereço com base nos ID's de pessoa e endereço
     *
     * @param enderecoId (identificador do endereço)
     * @param pessoaId (identificador da pessoa que possui o endereço)
     * @return O endereço referente aos ID's informados
     * @throws EntidadeNaoEncontradaException (caso a pessoa não possua o
     * endereço em sua lista ou o endereço não exista)
     */
    public Endereco findById(Long enderecoId, Long pessoaId) {
        return repository.findById(enderecoId, pessoaId).orElseThrow(()
                -> new EntidadeNaoEncontradaException(String.format(MSG_NOT_FOUND, pessoaId, enderecoId)));
    }

    /**
     * Salva um endereço no banco de dados
     *
     * @param endereco (endereço a ser salvo)
     * @return Endereco após salvo
     */
    @Transactional
    public Endereco save(Endereco endereco) {
        //verificando se o usuário passou um ID correto para cidade
        var cidade = serviceCidade.findById(endereco.getCidade().getId());
        endereco.setCidade(cidade);

        //se a lista de endereços da pessoa estiver vazia, o primeiro é salvo como principal automaticamente
        if (endereco.getPessoa().getEnderecos().isEmpty()) {
            endereco.tornarPrincipal();
        }

        return repository.save(endereco);
    }

    /**
     * Escolhe um endereço como principal de uma pessoa
     *
     * @param endereco (endereço escolhido como principal)
     */
    @Transactional
    public void escolherEnderecoPrincipal(Endereco endereco) {
        endereco.getPessoa().getEnderecos().forEach(item -> {
            if (item.equals(endereco)) {
                //escolhe o novo endereço principal
                item.tornarPrincipal();

            } else if (item.getPrincipal()) {
                //remove o antigo do posto de principal
                item.deixarPrincipal();
            }
        });
        repository.flush();
    }

    /**
     * Em caso de exclusão de um endereço principal, se a lista de endereços de
     * uma pessoa não estiver vazia, o primeiro elemento da lista se torna o
     * novo principal.
     *
     * @param enderecos (lista de endereços de uma pessoa)
     */
    public void elegerNovoPrincipal(List<Endereco> enderecos) {
        if (enderecos != null && !enderecos.isEmpty()) {
            var primeiro = enderecos.get(0);
            primeiro.tornarPrincipal();
            repository.flush();
        }
    }

    /**
     * Remove um endereco do banco de dados
     *
     * @param endereco (endereço a ser removido)
     */
    @Transactional
    public void removerEndereco(Endereco endereco) {
        repository.delete(endereco);
        repository.flush();
    }
}
