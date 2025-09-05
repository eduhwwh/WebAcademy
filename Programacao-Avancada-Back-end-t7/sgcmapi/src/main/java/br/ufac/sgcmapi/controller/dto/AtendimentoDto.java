package br.ufac.sgcmapi.controller.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public record AtendimentoDto(
    Long id,
    @NotNull
    @FutureOrPresent
    LocalDate data,
    @NotNull
    LocalTime hora,
    @NotNull
    Long profissional_id,
    String profissional_nome,
    String unidade_nome,
    @NotNull
    Long convenio_id,
    String convenio_nome,
    @NotNull
    Long paciente_id,
    String paciente_nome,
    String status
) {
    
}
