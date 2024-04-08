package com.ddinnovations.loadsystem.infrastructure.points.advice;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class ErrorHandler {
    private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerGeneralExceptions(Exception exception,
                                                                  HttpServletRequest request,
                                                                  WebRequest webRequest) {
        if (exception instanceof HttpClientErrorException) {
            return this.handleHttpClientErrorException(exception, request, webRequest);
        } else if (exception instanceof AccessDeniedException) {
            return this.handleAccessDeniedException((AccessDeniedException) exception, request, webRequest);
        } else if (exception instanceof MissingServletRequestParameterException) {
            return this.handleParamsNull((MissingServletRequestParameterException) exception, request, webRequest);
        } else if (exception instanceof NullPointerException) {
            return this.handlerNullPointer((NullPointerException) exception, request, webRequest);
        } else if (exception instanceof MethodArgumentNotValidException) {
            return this.handlerArgumentNotValid((MethodArgumentNotValidException) exception, request, webRequest);

        } else if (exception instanceof TransactionSystemException) {
            return this.handlerTransactionException((TransactionSystemException) exception, request, webRequest);

        } else if (exception instanceof BadCredentialsException) {
            return this.handlerBadCredentials((BadCredentialsException) exception, request, webRequest);

        } else {
            return this.handlerGenericExceptions(exception, request, webRequest);
        }
    }

    private ResponseEntity<ErrorResponse> handlerBadCredentials(BadCredentialsException exception, HttpServletRequest request, WebRequest webRequest) {
        ErrorResponse dto = new ErrorResponse("La contraseña es invalida",
                exception.getMessage(),
                request.getMethod(),
                request.getRequestURL().toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);

    }


    private ResponseEntity<ErrorResponse> handlerTransactionException(TransactionSystemException exception, HttpServletRequest request, WebRequest webRequest) {
        ErrorResponse dto = new ErrorResponse("Ocurrio un error en la base de datos , intentalo nuevamente",
                exception.getMessage(),
                request.getMethod(),
                request.getRequestURL().toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    private ResponseEntity<ErrorResponse> handlerArgumentNotValid(MethodArgumentNotValidException exception, HttpServletRequest request, WebRequest webRequest) {
        ErrorResponse dto = new ErrorResponse("Se esta enviando un valor nulo",
                exception.getMessage(),
                request.getMethod(),
                request.getRequestURL().toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    private ResponseEntity<ErrorResponse> handlerNullPointer(NullPointerException exception, HttpServletRequest request, WebRequest webRequest) {
        ErrorResponse dto = new ErrorResponse("Se esta enviando un valor nulo",
                exception.getMessage(),
                request.getMethod(),
                request.getRequestURL().toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);
    }

    private ResponseEntity<ErrorResponse> handleParamsNull(MissingServletRequestParameterException exception, HttpServletRequest request, WebRequest webRequest) {

        ErrorResponse dto = new ErrorResponse("El parametro " + exception.getParameterName() + " es requerido",
                exception.getMessage(),
                request.getMethod(),
                request.getRequestURL().toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);
    }

    //TODO:MANEJADOR
    private ResponseEntity<ErrorResponse> handlerGenericExceptions(Exception exception,
                                                                   HttpServletRequest request,
                                                                   WebRequest webRequest) {
        ErrorResponse dto = new ErrorResponse(exception.getMessage(),
                exception.getMessage(),
                request.getMethod(),
                request.getRequestURL().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    private ResponseEntity<ErrorResponse> handleHttpClientErrorException(Exception exception,
                                                                         HttpServletRequest request,
                                                                         WebRequest webRequest) {
        ErrorResponse dto = new ErrorResponse("Error inesperado al realizar la consulta",
                exception.getMessage(),
                request.getMethod(),
                request.getRequestURL().toString());
        return ResponseEntity.status(500).body(dto);
    }


    private ResponseEntity<ErrorResponse> handleAccessDeniedException(
            AccessDeniedException exception,
            HttpServletRequest request,
            WebRequest webRequest) {
        ErrorResponse dto = new ErrorResponse("No tienes acceso a este recurso",
                exception.getMessage(),
                request.getMethod(),
                request.getRequestURL().toString());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(dto);
    }
}
