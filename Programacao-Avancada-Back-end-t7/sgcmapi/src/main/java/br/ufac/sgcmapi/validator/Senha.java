package br.ufac.sgcmapi.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HorarioAtendimentoValidator.class)
public @interface Senha {
    String message() default "deve conter no minimo 1 letra maiuscula e 1 minuscula ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
