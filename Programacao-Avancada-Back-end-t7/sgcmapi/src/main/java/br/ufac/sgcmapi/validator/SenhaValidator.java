package br.ufac.sgcmapi.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SenhaValidator implements ConstraintValidator<Senha, String> {

    @Override
    public boolean isValid(String senha, ConstraintValidatorContext context) {

        if (senha != null && !senha.isBlank()) {
            if (senha.chars().noneMatch(Character::isLowerCase))
                return false;
            if (senha.chars().noneMatch(Character::isUpperCase))
                return false;
            if (senha.chars().noneMatch(Character::isDigit))
                return false;
            if (senha.length() < 8)
                return false;

        }

        return true;
    }

}
