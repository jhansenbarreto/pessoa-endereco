package com.attornatus.domain.exception;

/**
 * Exceção para casos de entidades em uso, quando estão acopladas a outras
 * entidades e não podem ser excluídas (Status HTTP 409 - CONFLICT)
 *
 * @author Jhansen Barreto
 */
public class EntidadeNaoEncontradaException extends RuntimeException {

    public EntidadeNaoEncontradaException(String msg) {
        super(msg);
    }
}
