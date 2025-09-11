package br.ufac.sgcmapi.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ProfissionalDto(
    Long id,
    @NotBlank
    String nome,
    @NotBlank
    String registroConselho,
    @NotBlank
    @Pattern(
        regexp = "^\\(\\d{2}\\) \\d{4,5}-\\d{4}$",
        message = "deve seguir o formato (68) 1234-5678")
    String telefone,
    @NotBlank
    String email,
    @NotNull
    Long especialidade_id,
    String especialidade_nome,
    @NotNull
    Long unidade_id,
    String unidade_nome
) {
    
}
