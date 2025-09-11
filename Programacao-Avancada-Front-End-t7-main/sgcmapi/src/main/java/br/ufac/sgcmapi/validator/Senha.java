package br.ufac.sgcmapi.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SenhaValidator.class)
public @interface Senha {
    String message() default "deve conter no mínimo 1 letra maiúscula, 1 minúscula, 1 número, e 8 caracteres.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};    
}
