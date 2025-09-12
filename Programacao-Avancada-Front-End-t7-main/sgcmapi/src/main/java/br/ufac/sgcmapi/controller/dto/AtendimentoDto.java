package br.ufac.sgcmapi.controller.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import br.ufac.sgcmapi.validator.HorarioAtendimento;
import br.ufac.sgcmapi.validator.grupos.OnCreate;
import br.ufac.sgcmapi.validator.grupos.OnUpdate;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public record AtendimentoDto(
    @NotNull(groups = OnUpdate.class)
    Long id,
    @NotNull(groups = { OnCreate.class, OnUpdate.class })
    @FutureOrPresent(groups = { OnCreate.class, OnUpdate.class })
    LocalDate data,
    @NotNull(groups = { OnCreate.class, OnUpdate.class })
    @HorarioAtendimento(groups = { OnCreate.class, OnUpdate.class })
    LocalTime hora,
    @NotNull(groups = { OnCreate.class, OnUpdate.class })
    Long profissional_id,
    String profissional_nome,
    String unidade_nome,
    Long convenio_id,
    String convenio_nome,
    @NotNull(groups = { OnCreate.class, OnUpdate.class })
    Long paciente_id,
    String paciente_nome,
    String status
) {
    
}
