import { CanActivateFn } from '@angular/router';
import { LoginService } from './login.service';
import { inject } from '@angular/core';

export const autenticacaoGuard: CanActivateFn = (route, state) => {
  const servicoLogin = inject(LoginService);
  const papelExigido = route.data['papel'];
  const papelUsuario = servicoLogin.usuarioAutenticado().papel;
  const podeAcessar = papelExigido == papelUsuario || !papelExigido;
  return podeAcessar;
};
