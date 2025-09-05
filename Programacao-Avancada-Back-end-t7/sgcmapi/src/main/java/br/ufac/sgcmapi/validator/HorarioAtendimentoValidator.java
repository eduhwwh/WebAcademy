package br.ufac.sgcmapi.validator;

import java.time.LocalTime;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class HorarioAtendimentoValidator implements ConstraintValidator<HorarioAtendimento, LocalTime> {

    @Override
    public boolean isValid(LocalTime hora, ConstraintValidatorContext context) {
        if (hora == null) {
            return false;
        }
        var limiteInicial = LocalTime.of(14, 0);
        var limiteFinal = LocalTime.of(20, 0);
        var isValido = !hora.isBefore(limiteInicial) && !hora.isAfter(limiteFinal);
        return isValido;
    }
    
}
