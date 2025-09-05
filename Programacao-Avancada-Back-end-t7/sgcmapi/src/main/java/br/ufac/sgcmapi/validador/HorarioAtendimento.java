package br.ufac.sgcmapi.validador;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HorarioAtendimentoValidator.class)
public @interface HorarioAtendimento {

    String message() default "Fora do horario de atendimento(14:00h =- 20:00h)";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
    
}
