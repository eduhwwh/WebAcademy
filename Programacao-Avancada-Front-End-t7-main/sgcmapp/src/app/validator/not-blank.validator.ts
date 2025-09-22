import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";

export function notBlankValidator(tamanhoMinimo?: number): ValidatorFn {
    return (controle: AbstractControl): ValidationErrors | null => {
        tamanhoMinimo = tamanhoMinimo || 1;
        if (controle.value == null || controle.value.trim().length < tamanhoMinimo) {
            return { notBlank: true }
        }
        return null;
    }
}
