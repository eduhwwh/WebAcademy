package br.ufac.sgcmapi.controller.dto;

import br.ufac.sgcmapi.model.EPapel;
import br.ufac.sgcmapi.model.Usuario;

public record UsuarioDto(
    Long id,
    String nomeCompleto,
    String nomeUsuario,
    String senha,
    String papel,
    boolean ativo
) {

    
    
}
