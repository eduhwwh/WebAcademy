package br.ufac.sgcmapi.controller;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.ufac.sgcmapi.model.RespostaErro;

@RestControllerAdvice
public class ControllerExcecoes {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespostaErro> notValid(MethodArgumentNotValidException e) {
        var mensagensErro = new ArrayList<String>();
        e.getBindingResult().getAllErrors().forEach(erro -> {
            String nomeCampo = ((FieldError) erro).getField();
            String mensagemPadrao = erro.getDefaultMessage();
            String mensagem = nomeCampo + " " + mensagemPadrao;
            mensagensErro.add(mensagem);
        });
        var resposta = new RespostaErro(mensagensErro);
        return ResponseEntity.badRequest().body(resposta);
    }
    
}
