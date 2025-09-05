package br.ufac.sgcmapi.controller.dto;

import br.ufac.sgcmapi.validator.grupos.OnCreate;
import jakarta.validation.constraints.NotNull;

public record UsuarioDto(
    Long id,
    String nomeCompleto,
    String nomeUsuario,
    @NotNull(groups = OnCreate.class)
    String senha,
    String papel,
    boolean ativo
) {
    
}
