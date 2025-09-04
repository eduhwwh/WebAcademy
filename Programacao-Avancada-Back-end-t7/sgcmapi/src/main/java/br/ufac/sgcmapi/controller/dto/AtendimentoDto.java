package br.ufac.sgcmapi.controller.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record AtendimentoDto(
    Long id,
    LocalDate data,
    LocalTime hora,
    Long profissional_id,
    String profissional_nome,
    String unidade_nome,
    Long convenio_id,
    String convenio_nome,
    Long paciente_id,
    String paciente_nome,
    String status
) {
    
}
