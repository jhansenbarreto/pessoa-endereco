package com.attornatus.api.exceptionhandler;

import com.attornatus.domain.exception.DadosDuplicadosException;
import com.attornatus.domain.exception.EntidadeEmUsoException;
import com.attornatus.domain.exception.EntidadeNaoEncontradaException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

import java.time.OffsetDateTime;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Classe tratadora de exceções. Toda e qualquer exceção lançada pela API é
 * tratada nesta classe, retornando ao consumidor da API informações mais
 * organizadas sobre o erro.
 *
 * @author Jhansen Barreto
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    private final static String MSG_GENERICA_ERRO
            = "ERRO INTERNO. Tente novamente e caso o erro persista, contate o administrador.";

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
            HttpStatusCode statusCode, WebRequest request) {

        var status = (HttpStatus) statusCode;

        if (body == null) {
            body = Error.builder()
                    .status(status.value())
                    .title(status.getReasonPhrase())
                    .detail(MSG_GENERICA_ERRO)
                    .timestamp(OffsetDateTime.now())
                    .build();

        } else if (body instanceof String stringBody) {
            body = Error.builder()
                    .status(status.value())
                    .title(stringBody)
                    .detail(MSG_GENERICA_ERRO)
                    .timestamp(OffsetDateTime.now())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        var errors = ex.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    String name = error.getObjectName();
                    if (error instanceof FieldError fieldError) {
                        name = fieldError.getField();
                    }
                    return Error.OriginError.builder()
                            .name(name)
                            .message(messageSource.getMessage(error, LocaleContextHolder.getLocale()))
                            .build();
                }).toList();

        var detail = "Um ou mais campos estão inválidos. Corrija e tente novamente.";
        var erro = builderError((HttpStatus) status, detail).errors(errors);

        return handleExceptionInternal(ex, erro.build(), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {

        var detail = String.format("O recurso %s que você tentou acessar não existe", ex.getRequestURL());
        var erro = builderError((HttpStatus) status, detail);

        return handleExceptionInternal(ex, erro.build(), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        var causaRaiz = ex.getRootCause();

        if (causaRaiz != null) {
            if (causaRaiz instanceof InvalidFormatException exception) {
                return handleInvalidFormat(exception, new HttpHeaders(), status, request);

            } else if (causaRaiz instanceof PropertyBindingException exception) {
                return handlePropertyBinding(exception, new HttpHeaders(), status, request);
            }
        }

        var erro = builderError((HttpStatus) status,
                "Corpo da requisição não pôde ser entendido. Verifique a sintaxe.");

        return handleExceptionInternal(ex, erro.build(), new HttpHeaders(), status, request);
    }

    private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {

        var detail = String.format(
                "O valor '%s' informado para a propriedade '%s' não é compatível com o tipo %s",
                ex.getValue(), joinPath(ex.getPath()), ex.getTargetType().getSimpleName());

        var erro = builderError((HttpStatus) status, detail);

        return handleExceptionInternal(ex, erro.build(), headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {

        var detail = String.format("A propriedade '%s' não existe. Corrija e tente novamente.", joinPath(ex.getPath()));
        var erro = builderError((HttpStatus) status, detail);

        return handleExceptionInternal(ex, erro.build(), headers, status, request);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
            WebRequest request) {

        var detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s' que é incompatível com o tipo %s",
                ex.getParameter().getParameterName(), ex.getValue(), ex.getParameter().getParameterType().getSimpleName());

        var status = HttpStatus.BAD_REQUEST;
        var erro = builderError(status, detail);

        return handleExceptionInternal(ex, erro.build(), new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex, WebRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var erro = builderError(status, ex.getMessage());

        return handleExceptionInternal(ex, erro.build(), new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUso(EntidadeEmUsoException ex, WebRequest request) {
        var status = HttpStatus.CONFLICT;
        var erro = builderError(status, ex.getMessage());

        return handleExceptionInternal(ex, erro.build(), new HttpHeaders(), status, request);
    }

    @ExceptionHandler(DadosDuplicadosException.class)
    public ResponseEntity<?> handleDadosDuplicados(DadosDuplicadosException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var erro = builderError(status, ex.getMessage());

        return handleExceptionInternal(ex, erro.build(), new HttpHeaders(), status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneric(Exception ex, WebRequest request) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var erro = builderError(status, MSG_GENERICA_ERRO);
        
        return handleExceptionInternal(ex, erro.build(), new HttpHeaders(), status, request);
    }

    /**
     * Monta o objeto que representa o erro causador da exceção.
     *
     * @param status (Status HTTP)
     * @param detail (Informação adicional sobre o erro)
     * @return Builder do objeto Error
     */
    private Error.ErrorBuilder builderError(HttpStatus status, String detail) {
        return Error.builder()
                .status(status.value())
                .title(status.getReasonPhrase())
                .detail(detail)
                .timestamp(OffsetDateTime.now());
    }

    /**
     * Une os campos referentes a um objeto causador de um erro. Exemplo: O
     * objeto 'endereco' foi informado com a propriedade 'cep' em branco. O
     * campo informado na representação do erro será 'endereco.cep', ou seja, os
     * campos de um objeto são unidos por um caracter ponto (.)
     *
     * @param references (campos que originaram os erros causadores da exceção)
     * @return Campos unidos por um ponto (.)
     */
    private String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(item -> item.getFieldName())
                .collect(Collectors.joining("."));
    }
}
