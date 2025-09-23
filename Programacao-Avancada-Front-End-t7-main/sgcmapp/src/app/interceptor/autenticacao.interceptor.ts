import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { LoginService } from '../service/login.service';

export const autenticacaoInterceptor: HttpInterceptorFn = (req, next) => {
  const servicoLogin = inject(LoginService);
  req = servicoLogin.alterarCabecalho(req);
  return next(req);
};
